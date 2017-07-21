package komorebi.bean.editor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import komorebi.bean.editor.attributes.buttonlink.Linkable;
import komorebi.bean.editor.objects.ExtendableObject;
import komorebi.bean.editor.objects.MultiTileObject;
import komorebi.bean.editor.objects.OnePerLevelObject;
import komorebi.bean.editor.objects.SingleTileObject;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.editor.objects.utils.Shape;
import komorebi.bean.editor.tools.clickanddrag.Selection;

public class EditorLevel {

  CategorizedSet2<TileObject> allObjects;

  public EditorLevel()
  {
    allObjects = new CategorizedSet2<TileObject>(TileObject.class);
  }

  public void render()
  { 
    for (TileObject obj: allObjects)
    {
      obj.render();
    }
  }

  public void addObject(TileObject object)
  {
    allObjects.add(object);
  }

  public HashSet<MultiTileObject> getMultiTileObjects()
  {
    return allObjects.getAllOfType(MultiTileObject.class);
  }

  public HashSet<SingleTileObject> getSingleTileObjects()
  {
    return allObjects.getAllOfType(SingleTileObject.class);
  }

  public HashSet<TileObject> getAllObjects()
  {
    return allObjects.getAll();
  }

  public HashSet<ExtendableObject> getExtendableObjects()
  {
    return allObjects.getAllOfType(ExtendableObject.class);
  }
  
  public HashSet<Linkable> getLinkableObjects()
  {
    return allObjects.getAllOfType(Linkable.class);
  }

  public void removeObject(TileObject obj)
  {
    allObjects.remove(obj);

  }

  public HashSet<TileObject> getTileObjects()
  {
    return allObjects.getAllOfType(TileObject.class);
  }


  public boolean alreadyHas(Class<? extends OnePerLevelObject> classType)
  {
    return !allObjects.getAllOfType(classType).isEmpty();
  }

  public OnePerLevelObject getOnePerLevelObject(Class<? extends OnePerLevelObject> classType)
  {
    for (Iterator<? extends OnePerLevelObject> it = allObjects.getAllOfType(classType).iterator(); 
        it.hasNext();)
    {
      return it.next();
    }

    throw new RuntimeException("No such object as " + classType.getSimpleName() + 
        " exists in the level");

  }

  public boolean locationOccupied(Point location)
  {
    for (TileObject object: allObjects)
    {
      if (object.containsPoint(location.x, location.y))
        return true;
    }

    return false;
  }
  
  public TileObject getFirstObjectFoundAt(Point location)
  {
    for (TileObject object: allObjects)
    {
      if (object.containsPoint(location.x, location.y))
        return object;
    }
    
    throw new RuntimeException("No object found at " + location);
  }

  public boolean willOverlapOtherObject(Shape rectangle)
  {    
    for (TileObject obj: getTileObjects())
    {      
      if (obj.wouldIntersect(rectangle))
        return true;
    }

    return false;
  }
  
  public boolean willOverlapOtherObjectExcludeSelf(Shape rectangle,
      TileObject self)
  {
    for (TileObject obj: getTileObjects())
    {      
      if (obj.wouldIntersect(rectangle) && obj != self)
        return true;
    }

    return false;
  }

  public ArrayList<TileObject> allObjectsWithin(Selection selection)
  {
    ArrayList<TileObject> objects = new ArrayList<TileObject>();

    for (TileObject obj: allObjects)
    {
      if (selection.intersects(obj.getArea()))
      {
        objects.add(obj);
      }
    }

    return objects;
  }
  
  public ArrayList<TileObject> allObjectsWithinExcluding(Selection selection,
      ArrayList<TileObject> exclude)
  {
    ArrayList<TileObject> objects = new ArrayList<TileObject>();

    for (TileObject obj: allObjects)
    {
      if (selection.intersects(obj.getArea()) &&
          !exclude.contains(obj))
      {
        objects.add(obj);
      }
    }

    return objects;
  }
}
