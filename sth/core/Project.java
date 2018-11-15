package sth.core;

import java.io.Serializable;

/**
 * School implementation.
 */

 public class Project implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private String _description;
	private boolean _isOpen;

	public Project(String name) {
		_name = name;
		_isOpen = true;
	}
}