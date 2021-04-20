package com.imedia.dto;

import java.util.Collection;

import org.springframework.data.domain.Page;

public class ListResponse<T> {

	private Collection<T> items;
	
	/*** 
	 * last page number 
	 ***/
	private Long pagescount;
	
	/*** 
	 * count of all items without pagination 
	 ***/
	private Long itemscount;
	
	
	
	
	
	public ListResponse() {
	}
	
	
	
	public ListResponse(Collection<T> items, Long pagescount, Long itemscount) {
		this.items      = items;
		this.pagescount = pagescount;
		this.itemscount = itemscount;
	}



	public ListResponse(Page<T> page) {
		items      = page.getContent();
		pagescount = (long) page.getTotalPages();
		itemscount = page.getTotalElements();
	}



	public Collection<T> getItems() {
		return items;
	}
	public void setItems(Collection<T> items) {
		this.items = items;
	}
	public Long getPagescount() {
		return pagescount;
	}
	public void setPagescount(Long pagescount) {
		this.pagescount = pagescount;
	}
	public Long getItemscount() {
		return itemscount;
	}
	public void setItemscount(Long itemscount) {
		this.itemscount = itemscount;
	}
	
	
}
