package com.horaz.todolist.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.user.client.Event;
import com.horaz.client.util.PageManager;
import com.horaz.client.widgets.events.ItemClickListener;
import com.horaz.client.widgets.events.TapListener;

/**
 * Entry point
 */
public class TodoListController implements EntryPoint {
	private TodoItemPage itemPage;
	private TodoIndexPage indexPage;
	private TodoItemDialog itemDialog;
	private TodoItem lastSelectedItem;

	@Override
	public void onModuleLoad() {
		PageManager.showPageLoadingMsg();

		itemDialog = new TodoItemDialog() {
			@Override
			protected void onDeleteClicked() {
				indexPage.getDatastore().remove(lastSelectedItem);
			}
			@Override
			protected void onDoneClicked() {
				lastSelectedItem.setField(TodoItem.FIELD_DONE, true);
				indexPage.getDatastore().update(lastSelectedItem);
			}
			@Override
			protected void onEditClicked() {
				itemPage.startEditing(lastSelectedItem);
				itemPage.show();
			}
		};
		indexPage = new TodoIndexPage();
		indexPage.getButtonNew().addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				// no editing, create new
				itemPage.startEditing(null);
				itemPage.show();
			}
		});
		itemPage = new TodoItemPage() {
			@Override
			protected void onNewTodoItem(TodoItem mdl) {
				indexPage.getDatastore().add(mdl);
			}
			@Override
			protected void onUpdateTodoItem(TodoItem mdl) {
				indexPage.getDatastore().update(mdl);
			}
		};
		indexPage.getListView().addItemClickListener(new ItemClickListener<TodoItem>() {
			@Override
			public void onItemClick(Event event, TodoItem model, AnchorElement aElm) {
				lastSelectedItem = model;
				itemDialog.setSelectedItem(model);
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				PageManager.hidePageLoadingMsg();
			}
		});
	}
}
