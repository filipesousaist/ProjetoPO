package sth.core;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  //FIXME define object fields (attributes and, possibly, associations)
  private ArrayList<Person> _people;

  //FIXME implement constructors if needed
  
  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    //FIXME implement text file reader
  }
  
  //FIXME implement other methods
  public Collection<Person> getAllUsers(String str) {
	  ArrayList<Person> retList = new ArrayList<>();
	  for (Person p: _people) {
		  String name = p.getName();
		  if (name.contains(str)) 
			  retList.add(p);
	  }
	  return retList;
  }

}
