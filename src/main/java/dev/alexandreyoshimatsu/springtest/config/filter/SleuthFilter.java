package dev.alexandreyoshimatsu.springtest.config.filter;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
public class SleuthFilter extends GenericFilterBean {

    private Tracer tracer;

    private String traceId;

    private String contextPath;

    @Autowired
    public SleuthFilter(Tracer tracer) {
        this.tracer = tracer;
        this.traceId = "traceId";
        this.contextPath = "contextPath";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (StringUtils.isEmpty(httpServletResponse.getHeader(traceId))) {
            httpServletResponse.setHeader(traceId, tracer.currentSpan().context().traceIdString());
        }

        if (StringUtils.isEmpty(httpServletResponse.getHeader(contextPath))) {
            httpServletResponse.setHeader(contextPath, String.valueOf(httpServletRequest.getRequestURI()));
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { /**/ }
}
