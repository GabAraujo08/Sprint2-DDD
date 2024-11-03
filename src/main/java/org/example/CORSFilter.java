package org.example;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Método de inicialização, se necessário
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Configurando os cabeçalhos CORS
        httpResponse.setHeader("Access-Control-Allow-Origin", "https://mecanicaagil-frontend.vercel.app");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Continue com a próxima parte da cadeia de filtros
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Método de destruição, se necessário
    }
}
