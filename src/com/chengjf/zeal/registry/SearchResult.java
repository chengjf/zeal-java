package com.chengjf.zeal.registry;

public class SearchResult {

	private String name;
	private String parentName;
	private String type;
	private Docset docset;
	private String path;
	private String query;

	public boolean lower(SearchResult r) {
		boolean lhsStartsWithQuery = this.name.startsWith(query);
		boolean rhsStartsWithQuery = r.name.startsWith(query);

		if (lhsStartsWithQuery != rhsStartsWithQuery) {
			return lhsStartsWithQuery;
		}

		int namesCmp = this.name.compareTo(r.getName());
		if (namesCmp != 0) {
			return namesCmp < 0;
		}

		int parentCmp = this.parentName.compareTo(r.getParentName());

		return parentCmp < 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Docset getDocset() {
		return docset;
	}

	public void setDocset(Docset docset) {
		this.docset = docset;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
