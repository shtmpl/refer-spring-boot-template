package refer.spring.boot.template.domain;

public abstract class AbstractPersistable {

    public abstract Long getId();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (null == other || getClass() != other.getClass()) {
            return false;
        }

        AbstractPersistable that = (AbstractPersistable) other;

        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
