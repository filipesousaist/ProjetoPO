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

	public Project(String name, String descr) {
		this(name);
		_description = descr;
	}

	public String getName() {
		return _name;
	}

	public boolean isOpen(){
		return _isOpen;
	}

	public void closeProject(){
		_isOpen = false;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && 
			obj instanceof Project && 
			_name.equals(((Project) obj)._name);
	}
}