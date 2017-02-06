package example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Component
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletResponse instanceof HttpServletResponse) {
            CollectingContentServletResponse newResponse = new CollectingContentServletResponse((HttpServletResponse) servletResponse);
            filterChain.doFilter(servletRequest, newResponse);
            String text = newResponse.toString();
            if (text != null) {
                text = text.replace("${restPath}", getBase((HttpServletRequest) servletRequest));
                servletResponse.getWriter().write(text);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getBase(HttpServletRequest req) {
        StringBuilder result = new StringBuilder();

        if (StringUtils.isNotBlank(req.getScheme())) {
            result.append(req.getScheme()).append("://");
        }

        if (StringUtils.isNotBlank(req.getServerName())) {
            result.append(req.getServerName());
            if (req.getServerPort() != -1) {
                result.append(":").append(req.getServerPort());
            }
        }

        if (StringUtils.isNotBlank(req.getContextPath())) {
            result.append(req.getContextPath());
        }

        String s = result.toString();
        return s.endsWith("/") ? s : s + "/";
    }

    @Override
    public void destroy() {

    }

    private class CollectingContentServletResponse extends HttpServletResponseWrapper {
        private CollectingServletOutputStream os;

        public CollectingContentServletResponse(HttpServletResponse response) {
            super(response);
            os = new CollectingServletOutputStream(new ByteArrayOutputStream());
        }

        public ServletOutputStream getOutputStream() throws IOException {
            return os;
        }

        public PrintWriter getWriter() throws IOException {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return os.getTarget().toString();
        }
    }

    private class CollectingServletOutputStream extends ServletOutputStream {
        OutputStream target;

        public CollectingServletOutputStream(OutputStream target) {
            this.target = target;
        }

        public void write(int b) throws IOException {
            target.write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }

        public OutputStream getTarget() {
            return target;
        }
    }
}

