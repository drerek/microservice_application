package com.techprimers.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;


public class PostFilter extends ZuulFilter {
  private static final Logger LOG = LoggerFactory.getLogger(PostFilter.class);
  @Override
  public String filterType() {
    return "post";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    try {
      RequestContext ctx = RequestContext.getCurrentContext();
      Object e = ctx.get("error.exception");

      if (e != null && e instanceof ZuulException) {
        ZuulException zuulException = (ZuulException)e;
        
        LOG.error("Zuul failure detected: " + zuulException.getMessage(), zuulException);

        // Remove error code to prevent further error handling in follow up filters
        ctx.remove("error.status_code");

        // Populate context with new response values
        ctx.setResponseBody("Overriding Zuul Exception Body");
        ctx.getResponse().setContentType("application/json");
        ctx.setResponseStatusCode(500); //Can set any error code as excepted
      }
    }
    catch (Exception ex) {
      LOG.error("Exception filtering in custom error filter", ex);
      ReflectionUtils.rethrowRuntimeException(ex);
    }
    return null;
  }

}