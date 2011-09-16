package com.horaz.todolist.client;

import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.user.client.Event;
import com.horaz.client.model.ValidationException;
import com.horaz.client.widgets.Button;
import com.horaz.client.widgets.Page;
import com.horaz.client.widgets.Toast;
import com.horaz.client.widgets.Toast.Duration;
import com.horaz.client.widgets.events.TapListener;

public abstract class TodoItemPage extends Page {
	private TodoItem editItem;

	public TodoItemPage() {
		// call super with the page element
		super(getElementById("page_item"));

		// set the save button tap listener
		Button btnSave = Button.byId("btn_save");
		btnSave.addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				try {
					FormElement form = (FormElement) getElementById("form_item");

					TodoItem mdl;
					if (editItem != null) {
						mdl = editItem;
					} else {
						mdl = new TodoItem();
					}
					mdl.setFields(form);

					if (editItem != null) {
						onUpdateTodoItem(mdl);
					} else {
						onNewTodoItem(mdl);
					}
				} catch (ValidationException e) {
					new Toast("Validation failed: "+e.getField().getName(), Duration.LONG).show();
				}
			}
		});
	}

	protected abstract void onNewTodoItem(TodoItem mdl);
	protected abstract void onUpdateTodoItem(TodoItem mdl);

	public void startEditing(TodoItem lastSelectedItem) {
		editItem = lastSelectedItem;
		if (editItem != null) {
			InputElement title = (InputElement) getElementById("title");
			title.setValue((String) editItem.getField(TodoItem.FIELD_TITLE));
			TextAreaElement notes = (TextAreaElement) getElementById("notes");
			notes.setValue((String) editItem.getField(TodoItem.FIELD_NOTES));
		}
	}
}
