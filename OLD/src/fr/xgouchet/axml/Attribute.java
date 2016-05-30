package fr.xgouchet.axml;

public class Attribute {

	/**
	 * @return the name
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return mPrefix;
	}

	/**
	 * @return the namespace
	 */
	public String getNamespace() {
		return mNamespace;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return mValue;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		mName = name;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(final String prefix) {
		mPrefix = prefix;
	}

	/**
	 * @param namespace
	 *            the namespace to set
	 */
	public void setNamespace(final String namespace) {
		mNamespace = namespace;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(final String value) {
		mValue = value;
	}

	private String mName, mPrefix, mNamespace, mValue;
}