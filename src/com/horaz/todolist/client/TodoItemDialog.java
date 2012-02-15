package com.horaz.todolist.client;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;
import com.horaz.client.widgets.Button;
import com.horaz.client.widgets.Page;
import com.horaz.client.widgets.events.TapListener;

public abstract class TodoItemDialog extends Page {
	private final Label label;

	public TodoItemDialog() {
		super(getElementById("dialog_item"));
		// get the placeholder <span>
		label = Label.wrap(getElementById("label_item"));
	}

	@Override
	public void onCreate() {
		// buttons
		Button btnDelete = Button.byId("btn_delete");
		btnDelete.addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				onDeleteClicked();
			}
		});
		Button btnDone = Button.byId("btn_done");
		btnDone.addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				onDoneClicked();
			}
		});
		Button btnEdit = Button.byId("btn_edit");
		btnEdit.addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				onEditClicked();
			}
		});
	}

	protected abstract void onDeleteClicked();
	protected abstract void onDoneClicked();
	protected abstract void onEditClicked();

	public void setSelectedItem(TodoItem itm) {
		label.setText((String) itm.getField(TodoItem.FIELD_TITLE));
	}
}
