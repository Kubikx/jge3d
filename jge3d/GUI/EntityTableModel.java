package jge3d.GUI;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import jge3d.Entity;
import jge3d.EntityList;

public class EntityTableModel extends AbstractTableModel {
    public static final int KEY = 0;
    public static final int VALUE = 1;
    private static final String[] columnNames = {"Key", "Value"};
    protected Vector<Object> dataVector;
    protected Entity ent;
    protected EntityList ent_list;

    public EntityTableModel(EntityList entity) {
        dataVector = new Vector<Object>();
        ent_list=entity;
        preFill();
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Object getValueAt(int row, int column) {
    	//System.out.print(dataVector.get(row) + "\n");
    	
    	//System.out.print("\n\n\n" + Window.getEntityView().getComboValue() + "\n\n\n");
    	
        ent = ent_list.getByName(Window.getEntityView().getComboValue());
        
        if(column == 0)
        	return dataVector.get(row);
        else if(column == 1) {
        	//If the entity actually exists
        	
        	//if(!ent.getType().equals("")) {
		    	if(dataVector.get(row).equals("type"))
		        	return ent.getType();
		        else if(dataVector.get(row).equals("positionX"))
		        	return ent.getPositionX();
		    	else if(dataVector.get(row).equals("positionY"))
		    		return ent.getPositionY();
				else if(dataVector.get(row).equals("positionZ"))
					return ent.getPositionZ();
				else if(dataVector.get(row).equals("transparent"))
					return ent.getTransparent();
				else if(dataVector.get(row).equals("alpha"))
					return ent.getAlpha();
				else if(dataVector.get(row).equals("texture_name"))
					return ent.getTextureName();
				else if(dataVector.get(row).equals("collidable"))		
					return ent.getCollidable();
				else if(dataVector.get(row).equals("size"))
					return ent.getSize();
				else if(dataVector.get(row).equals("ttl"))
					return ent.getTTL();
				else {
					System.out.print("SSHHIIITTTTT!!!! EntityTable value getting error(row not found)\n");
					return "?";
				}
        	//}
		    
		    /*
		    //If no entity has been instantiated yet
        	} else {
		    	if(dataVector.get(row).equals("type"))
		        	return "";
		        else if(dataVector.get(row).equals("positionX"))
		        	return 0.0f;
		    	else if(dataVector.get(row).equals("positionY"))
		    		return 0.0f;
				else if(dataVector.get(row).equals("positionZ"))
					return 0.0f;
				else if(dataVector.get(row).equals("texture_name"))
					return "";
				else if(dataVector.get(row).equals("collidable"))		
					return false;
				else if(dataVector.get(row).equals("size"))
					return 0.0f;
				else if(dataVector.get(row).equals("ttl"))
					return 0;
				else {
					System.out.print("SSHHIIITTTTT!!!! EntityTable value getting error(row not found)\n");
					return "?";
				}
        	}*/
        } else {
        	return "null";
        }
    }

    public void setValueAt(Object value, int row, int column) {
    	if(column == 0)
        	System.out.print("You can't set that dumbass\n");
        else if(column == 1) {
	        if(dataVector.get(row).equals("type"))
	        	ent.setType((String)value);
	        else if(dataVector.get(row).equals("positionX") || dataVector.get(row).equals("positionY") || dataVector.get(row).equals("positionZ"))
	        	ent.setPositionX((Float)value);
	    	else if(dataVector.get(row).equals("positionY"))
	    		ent.setPositionY((Float)value);
			else if(dataVector.get(row).equals("positionZ"))
				ent.setPositionZ((Float)value);
			else if(dataVector.get(row).equals("transparent"))
				ent.setTransparent((Boolean)value);
			else if(dataVector.get(row).equals("alpha"))
				ent.setAlpha((Float)value);
			else if(dataVector.get(row).equals("texture_name"))
				ent.setTextureName((String)value);
			else if(dataVector.get(row).equals("collidable"))		
				ent.setCollidable((Boolean)value);
			else if(dataVector.get(row).equals("size"))
				ent.setSize((Float)value);
			else if(dataVector.get(row).equals("ttl"))
				ent.setTTL((Integer)value);
			else {
				System.out.print("SSHHIIITTTTT!!!! EntityTable value setting error(row not found)\n");
			}
        }
        fireTableCellUpdated(row, column);
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public void preFill() {
    	for(String key: Entity.getKeys()) {
    		dataVector.add(key);
    		fireTableRowsInserted(
    			0,
    			dataVector.size()-1
    		);
    	}
    	
    }
   
    public void addEmptyRow() {
        dataVector.add(new String());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1
    	);
    }
}

