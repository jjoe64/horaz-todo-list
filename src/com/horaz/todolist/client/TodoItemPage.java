package com.horaz.todolist.client;

import com.google.gwt.user.client.Event;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Button;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Page;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Toast;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Toast.Duration;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.events.TapListener;

public class TodoItemPage extends Page {
	public TodoItemPage() {
		// call super with the page element
		super(getElementById("page_item"));

		// set the save button tap listener
		Button btnSave = Button.byId("btn_save");
		btnSave.addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				// we'll implement this later
				new Toast("todo...", Duration.SHORT).show();;
			}
		});
	}
}
