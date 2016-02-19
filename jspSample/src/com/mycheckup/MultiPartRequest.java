package com.mycheckup;

import java.io.*;
import java.util.Hashtable;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({ "rawtypes", "serial" })
public class MultiPartRequest extends Hashtable {

	private String encoding = "UTF-8";
	private String uploadDir = "";
	private byte[] buf = new byte[64 * 1024];
	private HttpServletRequest request = null;

	public MultiPartRequest(HttpServletRequest request, String encoding, String uploadDir) throws IOException {

		this.request = request;
		this.encoding = encoding;

		if (!uploadDir.endsWith(File.separator)) {

			this.uploadDir = uploadDir + File.separator;

		} else {

			this.uploadDir = uploadDir;
			this.checkUploadDir();
			this.parseData();
		}
	}

	public String getParameter(String value) {

		return (String) this.get(value);
	}

	public FileInfo getFileInfo(String value) {
		return (FileInfo) this.get(value);

	}

	private void checkUploadDir() throws IOException {

		File f = new File(this.uploadDir);

		if (!f.exists()) {
			f.mkdirs();
		}
	}

	private String getCheckedFileName(String filename) {

		int count = 1;

		String absPath = this.uploadDir + filename;
		while (new File(absPath).exists()) {

			filename = getOtherFileName(filename, count++);

			absPath = this.uploadDir + filename;

		}

		return filename;

	}

	private String getOtherFileName(String filename, int count) {

		int pos = 0;

		if ((pos = filename.lastIndexOf(".")) != -1)

			return filename.substring(0, pos) + "(" + count + ")" + filename.substring(pos);

		else

			return filename + "(" + count + ")";

	}

	private byte[] readLineBytes(ServletInputStream in) throws IOException {

		int count;

		int total = 0;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		count = in.readLine(buf, 0, buf.length);
		if (count != -1) {
			bos.write(buf, 0, count);
			total += count;
		}

		if (total == 0)
			return null;
		bos.close();

		return bos.toByteArray();

	}

	private String readLineString(ServletInputStream in) throws IOException {

		byte[] result = readLineBytes(in);

		if (result == null)
			return null;
		return removeBreakLine(new String(result, 0, result.length, encoding));
	}

	private String removeBreakLine(String str) {
		// TODO Auto-generated method stub

		if (str == null)
			return null;

		else if (str.endsWith("\r\n"))
			return str.substring(0, str.length() - 2);

		else if (str.endsWith("\n"))
			return str.substring(0, str.length() - 1);

		else

			return str;
	}

	private int getLineStringPosition(byte[] source) {

		if (source[source.length - 2] == '\r')
			return 2;

		else if (source[source.length - 1] == '\n')
			return 1;

		return 0;

	}

	private boolean compareBoundary(String boundary, byte ba[]) {

		if (boundary == null || ba == null)
			return false;

		for (int i = 0; i < boundary.length(); i++)
			if ((byte) boundary.charAt(i) != ba[i])
				return false;

		return true;
	}

	private int readPartData(ServletInputStream in, OutputStream os, String boundary, StringBuffer delim)
			throws IOException {

		int count = 0;

		byte[] copyData = null;
		byte[] result = null;

		while ((result = readLineBytes(in)) != null) {

			if (compareBoundary(boundary, result) == true) {
				String temp = new String(result, 0, result.length, "UTF-8");

				temp = removeBreakLine(temp);
				delim.append(temp);

				int pos = getLineStringPosition(copyData);

				os.write(copyData, 0, copyData.length - pos);
				count += copyData.length - pos;
				break;

			} else {

				if (copyData != null) {
					os.write(copyData, 0, copyData.length);
					count += copyData.length;
				}

				copyData = (byte[]) result.clone();
			}
		}
		return count;
	}

	private void parseData() throws IOException {

		int startPos = 0;
		//int endPos = 0;

		boolean exit_flag = false;
		boolean already_read_flag = false;

		String name = null;
		String value = null;
		String temp = null;

		String boundary = null;
		String endboundary = null;

		//int size = request.getContentLength();

		ServletInputStream in = request.getInputStream();

		while (!exit_flag) {
			if (already_read_flag != true)
				temp = readLineString(in);
			else
				already_read_flag = false;
			if (boundary == null) {

				boundary = temp;

				endboundary = boundary + "--";
			}

			if (endboundary.equals(temp)) {
				exit_flag = true;
			} else if (boundary.equals(temp)) {
				String head = readLineString(in);

				if (head.indexOf("Content-Disposition: from-data; name=\"") != -1) {

					if ((startPos = head.indexOf("filename=\"")) != -1) {
						name = head.substring(38, startPos - 3);
						value = head.substring(startPos + 10, head.length() - 1);

						if (value.equals("")) {
							readLineString(in);
							readLineString(in);
							readLineString(in);
							this.put(name, new FileInfo(value, 0));
							continue;

						}

						int pos = Math.max(value.lastIndexOf("\\"), value.lastIndexOf("/"));
						value = value.substring(pos + 1);
						value = getCheckedFileName(value);
						readLineString(in);
						readLineString(in);
						StringBuffer delim = new StringBuffer();

						int filesize = 0;
						BufferedOutputStream bos = null;

						try {
							bos = new BufferedOutputStream(new FileOutputStream(this.uploadDir + value));
							filesize = readPartData(in, bos, boundary, delim);
						} catch (IOException e) {
							throw e;
						} finally {
							bos.close();
						}

						temp = delim.toString();
						already_read_flag = true;
						this.put(name, new FileInfo(value, filesize));
						continue;

					} else {

						name = head.substring(38, head.length() - 1);
						readLineString(in);
						StringBuffer delim = new StringBuffer();
						ByteArrayOutputStream bos = new ByteArrayOutputStream();

						int filesize = readPartData(in, bos, boundary, delim);
						bos.close();
						temp = delim.toString();
						already_read_flag = true;
						byte[] result = bos.toByteArray();

						value = new String(result, 0, result.length, encoding);
						this.put(name, value);
						continue;

					}

				}
			}
		}

	}

	private int readFileData(ServletInputStream in, String filename, String boundary, StringBuffer delim)
			throws IOException {

		int count = 0;
		byte[] copyData = null;
		byte[] result = null;

		BufferedOutputStream bos = null;

		try {

			bos = new BufferedOutputStream(new FileOutputStream(filename));

			while ((result = readLineBytes(in)) != null) {

				if (compareBoundary(boundary, result) == true) {
					String temp = new String(result, 0, result.length, "UTF-8");
					temp = removeBreakLine(temp);
					delim.append(temp);
					int pos = getLineStringPosition(copyData);
					bos.write(copyData, 0, copyData.length);
					count += copyData.length - pos;
					break;
				} else {

					if (copyData != null) {
						bos.write(copyData, 0, copyData.length);
						count += copyData.length;
					}

					copyData = (byte[]) result.clone();

				}

			}

		} catch (IOException e) {

			throw e;
		} finally {
			bos.close();
		}

		return count;

	}

}
