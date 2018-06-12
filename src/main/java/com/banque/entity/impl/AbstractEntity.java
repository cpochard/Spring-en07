package com.banque.entity.impl;

import com.banque.entity.IEntity;

/**
 * Le classe abstraite qui represente toutes nos entites. <br>
 */
abstract class AbstractEntity implements IEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * Constructeur de l'objet. <br>
	 */
	protected AbstractEntity() {
		this(null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id de l'entite
	 */
	protected AbstractEntity(Integer unId) {
		super();
		this.setId(unId);
	}

	@Override
	public final Integer getId() {
		return this.id;
	}

	@Override
	public final void setId(Integer unId) {
		this.id = unId;
	}

	@Override
	public int hashCode() {
		if (this.getId() != null) {
			return (this.getClass().getName() + "-" + this.getId()).hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		// Class est un invariant on peut faire usage de == au lieu de equals
		if (obj instanceof IEntity && obj.getClass() == this.getClass()) {
			return ((IEntity) obj).getId() == this.getId() || ((IEntity) obj).getId().equals(this.getId());
		}
		return false;
	}

	/**
	 * Fabrique les informations complementaire a l'id. <br/>
	 * Sera appele par toString afin de completer la methode.
	 *
	 * @return les informations complementaire a l'id.
	 */
	protected abstract String asString();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{class=");
		sb.append(this.getClass().getName());
		sb.append(",id=");
		sb.append(this.getId());
		sb.append(',');
		sb.append(this.asString());
		sb.append("}");
		return sb.toString();
	}
}