package com.mct.practical.practical2.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Paging<T> {

    private static final int DEFAULT_PAGE_SIZE = 3;

    /**
     * Set in the constructor if the list is paged
     */
    private final boolean pagedList;

    private final List<T> list;
    /**
     * pageNumber : page current
     * Min : 1
     */
    private final int pageNumber;
    /**
     * pageNumber : total item per page
     * Min : 1
     */
    private final int pageSize;

    private final int totalPage;

    public Paging(@NotNull List<T> list, int pageNumber, int pageSize) {
        this(list, list.size(), pageNumber, pageSize, false);
    }

    public Paging(@NotNull List<T> list, long totalItem, int pageNumber, int pageSize) {
        this(list, totalItem, pageNumber, pageSize, true);
    }

    private Paging(@NotNull List<T> list, long totalItem, int pageNumber, int pageSize, boolean pagedList) {
        this.pagedList = pagedList;
        this.list = Objects.requireNonNull(list);
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
        this.totalPage = (int) Math.ceil((float) totalItem / pageSize);
        this.pageNumber = Math.min(Math.max(pageNumber, 1), totalPage + 1);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<T> getPagedList() {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        if (pagedList) {
            return list;
        }
        int size = list.size();
        int fromIndex = (pageNumber - 1) * pageSize;
        int endIndex = fromIndex + pageSize;
        if (fromIndex > size) {
            return Collections.emptyList();
        }
        return new ArrayList<>(list.subList(fromIndex, Math.min(endIndex, size)));
    }

    public String getPageContainer(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append(getOpenOrCloseTag(true));

        if (totalPage > 1 && pageNumber > 1 && pageNumber <= totalPage) {
            int prevPage = pageNumber - 1;
            sb.append(getPrevItem(url + prevPage));
        }
        for (int i = 1; i < totalPage + 1; i++) {
            sb.append(getPageItem(url + i, i, i == pageNumber));
        }
        if (totalPage > 1 && pageNumber >= 1 && pageNumber < totalPage) {
            int nextPage = pageNumber + 1;
            sb.append(getNextItem(url + nextPage));
        }

        sb.append(getOpenOrCloseTag(false));
        return sb.toString();
    }

    @Contract(pure = true)
    private @NotNull String getOpenOrCloseTag(boolean isOpen) {
        if (isOpen) {
            return "<div class=\"pagination-container\"><ul class=\"pagination\">";
        } else {
            return "</ul></div>";
        }
    }

    @Contract(pure = true)
    private @NotNull String getPageItem(String url, int page, boolean active) {
        if (active) {
            return "<li class=\"active\"><a>" + page + "</a></li>";
        } else {
            return "<li><a href=\"" + url + "\">" + page + "</a></li>";
        }
    }

    @Contract(pure = true)
    private @NotNull String getPrevItem(String url) {
        return " <li><a href=\"" + url + "\">«</a></li>";
    }

    @Contract(pure = true)
    private @NotNull String getNextItem(String url) {
        return " <li><a href=\"" + url + "\">»</a></li>";
    }

}
