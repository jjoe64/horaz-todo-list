package com.horaz.todolist.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point
 */
public class TodoListController implements EntryPoint {
	private TodoItemPage itemPage;

	@Override
	public void onModuleLoad() {
		itemPage = new TodoItemPage();
	}
}
