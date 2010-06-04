package jge3d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.vecmath.Vector3f;

import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;

public class Entity {
	private String name;
	private String type;
	private float mass;
	private Vector3f position;
	private Vector3f gravity;
	private String texture_name;
	private boolean collidable;
	private float size;
	private RigidBody phys_body;
	private int ttl;
	private long created_at;
	//private Physics physics;
	private boolean transparent = false;
	private float alpha=1.0f;
	private static String[] keys = {"name","type","positionX","positionY","positionZ","gravityX","gravityY","gravityZ","mass","transparent","alpha","texture_name","collidable","size","TTL"};
	protected static int num_entities=0;
	
	public Entity() {
		num_entities++;
		name="ent" + num_entities;
		position = new Vector3f();
		gravity = new Vector3f();
	}
	
	public Entity(String _name) {
		num_entities++;
		name=_name;
		position = new Vector3f();
		gravity = new Vector3f();
	}
	
	public Entity(String _type, Vector3f _pos, String _texture_name, boolean _collidable, int _ttl) {
		num_entities++;
		name="ent" + num_entities;
		type=_type;
		position=_pos;
		texture_name=_texture_name;
		collidable=_collidable;
		size=1.0f;
		ttl=_ttl;
		created_at=System.currentTimeMillis();
		gravity = new Vector3f();
	}

	public Entity(String _type, Vector3f _pos, String _texture_name, boolean _collidable, RigidBody rb, int _ttl) {
		num_entities++;
		type=_type;
		position=_pos;
		texture_name=_texture_name;
		collidable=_collidable;
		size=1.0f;
		phys_body=rb;
		ttl=_ttl;
		created_at=System.currentTimeMillis();
		gravity = new Vector3f();
	}

	public String getName() {
		return name;
	}
	public Vector3f getPosition() {
		return position;
	}
	public float getPositionX() {
		return position.x;
	}
	public float getPositionY() {
		return position.y;
	}
	public float getPositionZ() {
		return position.z;
	}
	public float getGravityX() {
		return gravity.x;
	}
	public float getGravityY() {
		return gravity.y;
	}
	public float getGravityZ() {
		return gravity.z;
	}
	public float getMass() {
		return mass;
	}
	public boolean getCollidable() {
		return collidable;
	}
	
	public String getTexture_name() {
		return texture_name;
	}
	
	public void setRigidBody(RigidBody rb) {
		phys_body = rb;
	}

	public Boolean getTransparent() {
		return transparent;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public float getSize() {
		return size;
	}
	
	public String getType() {
		return type;
	}
	
	public int getTTL() {
		return ttl;
	}
	
	public boolean isDead() {
		if( (System.currentTimeMillis() >= (created_at+ttl)) && (ttl != 0) ) {
			//System.out.print("RIP\n===\nBorn: " + created_at +
			//"\nDied:" +  System.currentTimeMillis() +  "\n" + "Lived: " +
			//((System.currentTimeMillis()-created_at)/1000.0f) + " sec\n");
			return true;
		} else {
			return false;
		}
	}

	public void setName(String _name) {
		try {
			name=_name;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for name, must be String\n");
		}
	}
	
	public void setPositionX(Object x) {
		try {
			position.x = Float.parseFloat(x.toString());
			updatePosition();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Incorrect data type for positionX, must be Float\n");
		}
	}
	
	public void setPositionY(Object y) {
		try {
			position.y = Float.parseFloat(y.toString());
			updatePosition();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Incorrect data type for positionY, must be Float\n");
		}
	}
	
	public void setPositionZ(Object z) {
		try {
			position.z = Float.parseFloat(z.toString());
			updatePosition();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Incorrect data type for positionZ, must be Float\n");
		}
	}
	
	public void setGravityX(Object x) {
		try {
			gravity.x = Float.parseFloat(x.toString());
			phys_body.setGravity(gravity);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for gravityX, must be Float\n");
		}
		
	}
	
	public void setGravityY(Object y) {
		try {
			gravity.y = Float.parseFloat(y.toString());
			phys_body.setGravity(gravity);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for gravityY, must be Float\n");
		}
	}
	
	public void setGravityZ(Object z) {
		try {
			gravity.z = Float.parseFloat(z.toString());
			phys_body.setGravity(gravity);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for gravityZ, must be Float\n");
		}
	}
	
	public void setTexture_name(Object name) {
		try {
			texture_name = name.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for name, must be String\n");
		}
	}
	
	public void setCollidable(Object _collidable) {
		try {
			collidable = Boolean.parseBoolean(_collidable.toString());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for collidable, must be Boolean\n");
		}
	}
	
	public void setType(Object _type) {
		try {
			type=_type.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for type, must be String\n");
		}
	}
	
	public void setMass(Object _mass) {
		try {
			mass = Float.parseFloat(_mass.toString());
			phys_body.setMassProps(mass, new Vector3f(0,0,0));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for mass, must be Float\n");
		}
	}
	
	public void setDamping(float lin_damping, float ang_damping) {
		try {
			phys_body.setDamping(lin_damping, ang_damping);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for damping, must be Float,Float\n");
		}
	}
	
	public void setFriction(Object friction) {
		try {
			phys_body.setFriction(Float.valueOf(friction.toString()));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for friction, must be Float\n");
		}
	}
	
	public void setSize(Object _size) {
		try {
			size = Float.valueOf(_size.toString());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for size, must be Float\n");
		}
	}
	
	public void setTTL(Integer _ttl) {
		try {
			ttl=Integer.valueOf(_ttl.toString());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.print("Incorrect data type for TTL, must be Integer(seconds)\n");
		}
	}
	
	public void setTransparent(Boolean _transparent) {
		try {
			transparent=Boolean.parseBoolean(_transparent.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setAlpha(Float _alpha) {
		try {
			alpha=Float.parseFloat(_alpha.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void set(String key, String value) {
		if(key.equals("name"))
			setName(name);
		else if(key.equals("type"))
        	setType(value);
        else if(key.equals("positionX"))
        	setPositionX(Float.valueOf(value));
    	else if(key.equals("positionY"))
    		setPositionY(Float.valueOf(value));
		else if(key.equals("positionZ"))
			setPositionZ(Float.valueOf(value));
		else if(key.equals("transparent"))
			setTransparent(Boolean.valueOf(value));
		else if(key.equals("alpha"))
			setAlpha(Float.valueOf(value));
		else if(key.equals("texture_name"))
			setTexture_name(value);
		else if(key.equals("collidable"))		
			setCollidable(Boolean.valueOf(value));
		else if(key.equals("size"))
			setSize(Float.valueOf(value));
		else if(key.equals("ttl"))
			setTTL(Integer.valueOf(value));
		else {
			System.out.print("SSHHIIITTTTT!!!! Entity parsing error");
		}
	}
	
	public void deletePhysics() {
		Physics.getInstance().getDynamicsWorld().removeRigidBody(phys_body);
	}
	
	public Object getFromMethod(String member, Entity ent) throws IllegalArgumentException, InvocationTargetException {
		Class<?> c;
		Method[] allMethods = null;
		String mname;
		Object o = null;
		try {
			c = Class.forName("jge3d.Entity");
			allMethods = c.getDeclaredMethods();
			for (Method m : allMethods) {
				mname = m.getName();
				
				if(mname.equals(member)) {
					o = m.invoke(ent);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public Object setFromMethod(String member, Entity ent, Object value) throws IllegalArgumentException, InvocationTargetException {
		Class<?> c;
		Method[] allMethods = null;
		String mname;
		Object o = null;
		try {
			c = Class.forName("jge3d.Entity");
			allMethods = c.getDeclaredMethods();
			for (Method m : allMethods) {
				mname = m.getName();
				
				if(mname.equals(member)) {
					System.out.print(mname + ": " + value + "\n");
					Object[] val_array = new Object[1];
					val_array[0] = value;
					o = m.invoke(ent,val_array);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}

	public String toString() {
		String out = 
			"\tEntity=" + name + "\n" +
				"\t\t" + "type=" + type + "\n" +
				"\t\t" + "positionX=" + position.x + "\n" +
				"\t\t" + "positionY=" + position.y + "\n" +
				"\t\t" + "positionZ=" + position.z + "\n" +
				"\t\t" + "gravityX=" + alpha + "\n" +
				"\t\t" + "gravityY=" + alpha + "\n" +
				"\t\t" + "gravityZ=" + alpha + "\n" +
				"\t\t" + "mass=" + alpha + "\n" +
				"\t\t" + "transparent=" + transparent + "\n" +
				"\t\t" + "alpha=" + alpha + "\n" +
				"\t\t" + "texture_name=" + texture_name + "\n" +
				"\t\t" + "collidable=" + collidable + "\n" +
				"\t\t" + "size=" + size + "\n" +
				"\t\t" + "TTL=" + ttl + "\n" +
			"\t/Entity\n";
			
		return out;
	}
	public void updatePosition() {
		Transform temp = new Transform();
		temp.origin.set(position);
		phys_body.setWorldTransform(temp);	
	}
	
	public static String[] getKeys() {
		return keys;
	}
	//private Vector3f axisLimits;
	//private Vector3f angleLimits;
	
}
