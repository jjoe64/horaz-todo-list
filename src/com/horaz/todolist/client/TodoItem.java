package com.horaz.todolist.client;

import com.jjoe64.gwtmobile_test.client.horaz.model.BaseModel;
import com.jjoe64.gwtmobile_test.client.horaz.model.Validation;

/**
 * own model class, that represent a single ToDo-list item
 *
 * Fields:
 *  - title (required)
 *  - notes (optional)
 *  - done (optional)
 */
public class TodoItem extends BaseModel {
	static final String FIELD_TITLE = "title";
	static final String FIELD_NOTES = "notes";
	static final String FIELD_DONE = "done";

	/**
	 * Every Model has to implement the getStructure method.
	 * This method must return the fields of the model.
	 * At this point we can also set a validation rule for the fields.
	 */
	@Override
	protected ModelField[] getStructure() {
		return new ModelField[] {
				new ModelField(FIELD_TITLE, Validation.IS_NOT_EMPTY)
				, new ModelField(FIELD_NOTES)
				, new ModelField(FIELD_DONE)
		};
	}

	/**
	 * @return done flag
	 */
	public boolean isDone() {
		Boolean b = (Boolean) getField(TodoItem.FIELD_DONE);
		return b==null?false:b;
	}
}
