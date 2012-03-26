package biz.company.qa.reporting;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a helper class that contains all the known data of a test. We can then create a DOM-tree out of this objects
 * via DOMCreator.
 * 
 
 */
public class Test {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bugs == null) ? 0 : bugs.hashCode());
        result = prime * result + ((classname == null) ? 0 : classname.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((requirements == null) ? 0 : requirements.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Test other = (Test) obj;
        if (bugs == null) {
            if (other.bugs != null) {
                return false;
            }
        } else if (!bugs.equals(other.bugs)) {
            return false;
        }
        if (classname == null) {
            if (other.classname != null) {
                return false;
            }
        } else if (!classname.equals(other.classname)) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (notes == null) {
            if (other.notes != null) {
                return false;
            }
        } else if (!notes.equals(other.notes)) {
            return false;
        }
        if (requirements == null) {
            if (other.requirements != null) {
                return false;
            }
        } else if (!requirements.equals(other.requirements)) {
            return false;
        }
        if (result == null) {
            if (other.result != null) {
                return false;
            }
        } else if (!result.equals(other.result)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", name=" + name + ", classname=" + classname + ", date=" + date + ", requirements="
                + requirements + ", bugs=" + bugs + ", result=" + result + ", notes=" + notes + "]";
    }

    private String id;
    private String name;
    private String classname;
    private String date;
    private List<String> requirements = new ArrayList<String>();
    private List<String> bugs = new ArrayList<String>();
    private String result;
    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getBugs() {
        return bugs;
    }

    public void setBugs(List<String> bugs) {
        this.bugs = bugs;
    }

    public boolean addBug(String bug) {
        return bugs.add(bug);
    }

    public boolean addRequirement(String requirement) {
        return requirements.add(requirement);
    }
}
