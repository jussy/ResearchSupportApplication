package researchSupport;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * @author jussy
 * 
 */
public class Paper extends Vertex<Citation, Reference> {
	private String title;
	private int rating;

	/**
	 * Creates a new instance of Paper
	 * 
	 * @param title
	 * @param rating
	 */
	public Paper(String title, int rating) {
		this.setTitle(title);
		this.setRating(rating);
		this.setInEdges(new HashMap<String, Citation>());
		this.setOutEdges(new HashMap<String, Reference>());
		this.setNumReferences(0);
		this.setNumCitations(0);
	}

	/**
	 * 
	 * @param referee
	 * @return
	 */
	public boolean createReference(Paper referee) {
		this.getOutEdges().put(referee.getTitle(), new Reference(referee));
		this.setNumReferences(this.getNumReferences() + 1);
		return true;
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public boolean createCitation(Paper source) {
		this.getInEdges().put(source.getTitle(), new Citation(source));
		this.setNumCitations(getNumCitations() + 1);
		return true;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * 
	 * @return
	 */
	public HashSet<Paper> getCitations() {
		HashSet<Paper> cits = new HashSet<Paper>();
		for (Citation c : this.getInEdges().values()) {
			cits.add(c.getSource());
		}
		return cits;
	}

	/**
	 * 
	 * @return
	 */
	public HashSet<Paper> getReferences() {
		HashSet<Paper> refs = new HashSet<Paper>();
		for (Reference r : this.getOutEdges().values()) {
			refs.add(r.getReferee());
		}
		return refs;
	}

	/**
	 * @return the numReferences
	 */
	public int getNumReferences() {
		return getOutDegree();
	}

	/**
	 * @param numReferences
	 *            the numReferences to set
	 */
	public void setNumReferences(int numReferences) {
		this.setOutDegree(numReferences);
	}

	/**
	 * @return the numCitations
	 */
	public int getNumCitations() {
		return getInDegree();
	}

	/**
	 * @param numCitations
	 *            the numCitations to set
	 */
	public void setNumCitations(int numCitations) {
		this.setInDegree(numCitations);
	}

	@Override
	public String toString() {
		String rating = "";
		for (int i = 0; i < getRating(); i++) {
			rating += '*';
		}
		return "Title: " + getTitle()
				+ (getTitle().length() > 8 ? "\t" : "\t\t") + "Rating: "
				+ rating + "\t" + getNumReferences()
				+ " reference(s) | Cited by " + getNumCitations();
	}
}
