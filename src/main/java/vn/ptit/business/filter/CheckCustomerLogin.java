package vn.ptit.business.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/checkout","/my-order","/edit-profile","/cart","/view-profile" })
public class CheckCustomerLogin implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (req.getSession().getAttribute("customerLogin") != null) {
			chain.doFilter(request, response);
		}
		else resp.sendRedirect("/BookStoreOnline/login");

	}

}
