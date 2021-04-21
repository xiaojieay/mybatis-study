package org.apache.ibatis.cache;

/**
 * @author Clinton Begin
 * @deprecated Since 3.5.3, This class never used and will be removed future version.
 */
@Deprecated
public final class NullCacheKey extends CacheKey {

  private static final long serialVersionUID = 3704229911977019465L;

  public NullCacheKey() {
    super();
  }

  @Override
  public void update(Object object) {
    throw new CacheException("Not allowed to update a NullCacheKey instance.");
  }

  @Override
  public void updateAll(Object[] objects) {
    throw new CacheException("Not allowed to update a NullCacheKey instance.");
  }
}
