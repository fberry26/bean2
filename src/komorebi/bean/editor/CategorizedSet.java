package komorebi.bean.editor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CategorizedSet<T> implements Iterable<T> {

  private HashSet<T> objects;
  private HashMap<Class<? extends T>, HashSet<? extends T>> subHashsets;

  public CategorizedSet(Class<T> superClass)
  {
    objects = new HashSet<T>();

    subHashsets = new HashMap<Class<? extends T>, HashSet<? extends T>>();
  }

  @SuppressWarnings("unchecked")
  public <E extends T> void add(E add)
  {
    objects.add(add);

    for (Class<? extends T> subclass: subHashsets.keySet())
    {
      if (subclass.isInstance(add))
        ((HashSet<E>) (subHashsets.get(subclass))).add(add);
    }
  }


  @SuppressWarnings("unchecked")
  public <E extends T> HashSet<E> getAllOfType(Class<E> key)
  {

    if (!subHashsets.containsKey(key))
    {
      HashSet<E> addee = new HashSet<E>();
      subHashsets.put(key, addee);

      for (T object: objects)
      {
        if (key.isInstance(object))
          addee.add((E) object);
      }
    }

    return (HashSet<E>) subHashsets.get(key);
  }

  public HashSet<T> getAll()
  {
    return objects;
  }

  public void remove(T object)
  {
    objects.remove(object);

    for (HashSet<? extends T> subHashset: subHashsets.values())
    {
      if (subHashset.contains(object))
        subHashset.remove(object);
    }
  }

  @Override
  public Iterator<T> iterator() {
    return objects.iterator();
  }

}
