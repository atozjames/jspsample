package com.mycheckup;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;



public final class LoggingFilter implements Filter   {

	 private FilterConfig filterConfig = null;
	 static Logger logger2 = Logger.getLogger("process.work2");
	 static Logger logger4 = Logger.getLogger("process.work4");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		    long start = System.currentTimeMillis();
		    String address =  request.getRemoteAddr();
		    String file = ((HttpServletRequest) request).getRequestURI();
		        
		    chain.doFilter(request, response);

//		    filterConfig.getServletContext().log(
//		        "User access! " +      
//		        " User IP: " + address +      
//		        " Resource: " + file + 
//		        " Milliseconds used: " + (System.currentTimeMillis() - start) 
//		    );
//		    
		    logger2.info(
		    		
				"User access! " + " User IP: " + address + " Resource: " + file + " Milliseconds used: "
						+ (System.currentTimeMillis() - start)
		    		
		    		);
		    
		    logger4.info(
		    		
					"User access! " + " User IP: " + address + " Resource: " + file + " Milliseconds used: "
							+ (System.currentTimeMillis() - start)
			    		
			    		);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	    this.filterConfig = filterConfig;
	  }
	
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
   

}
