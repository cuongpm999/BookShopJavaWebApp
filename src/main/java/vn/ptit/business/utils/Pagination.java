package vn.ptit.business.utils;

import java.util.ArrayList;
import java.util.List;

import vn.ptit.model.book.BookItem;

public class Pagination {
	private static int pageSize = 24;

	public static List<BookItem> paging(List<BookItem> list, int current) {
		List<BookItem> res = new ArrayList<>();
		int totalCount = list.size();

		int start = (current - 1) * pageSize > totalCount ? totalCount : (current - 1) * pageSize;
		int end = current * pageSize > totalCount ? totalCount : current * pageSize;

		res = list.subList(start, end);
		return res;
	}

}
