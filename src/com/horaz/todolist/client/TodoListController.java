package com.horaz.todolist.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point
 */
public class TodoListController implements EntryPoint {
	private TodoItemPage itemPage;
	private TodoIndexPage indexPage;

	@Override
	public void onModuleLoad() {
		indexPage = new TodoIndexPage();
		itemPage = new TodoItemPage() {
			@Override
			protected void onNewTodoItem(TodoItem mdl) {
				indexPage.getDatastore().add(mdl);
			}
		};
	}
}
