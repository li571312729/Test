package com.designMode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

@SuppressWarnings("serial")
public final class $Proxy0 extends Proxy implements MoveAble {
  private static Method m1;
  private static Method m3;
  private static Method m2;
  private static Method m0;
  
  public $Proxy0(InvocationHandler paramInvocationHandler){
    super(paramInvocationHandler);
  }
  
  public final boolean equals(Object paramObject){
    try
    {
      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final void run(){
    try
    {
      this.h.invoke(this, m3, null);
      return;
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final String toString(){
    try
    {
      return (String)this.h.invoke(this, m2, null);
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final int hashCode(){
    try
    {
      return ((Integer)this.h.invoke(this, m0, null)).intValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  static
  {
      try {
        m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
        m3 = Class.forName("com.designMode.proxy.dynamic.MoveAble").getMethod("run", new Class[0]);
        m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
        m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
      } catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

    @Override
    public void move() {
        try
        {
          this.h.invoke(this, m3, null);
          return;
        }
        catch (Error|RuntimeException localError)
        {
          throw localError;
        }
        catch (Throwable localThrowable)
        {
          throw new UndeclaredThrowableException(localThrowable);
        }
    }
}
