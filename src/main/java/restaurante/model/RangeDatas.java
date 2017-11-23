package restaurante.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class RangeDatas {

	public RangeDatas() {
		super();
	}

	public RangeDatas(Date dataDe, Date dataAte) {
		super();
		this.dataDe = dataDe;
		this.dataAte = dataAte;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	public Date dataDe;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	public Date dataAte;

	public Date getDataDe() {
		return dataDe;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAte == null) ? 0 : dataAte.hashCode());
		result = prime * result + ((dataDe == null) ? 0 : dataDe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangeDatas other = (RangeDatas) obj;
		if (dataAte == null) {
			if (other.dataAte != null)
				return false;
		} else if (!dataAte.equals(other.dataAte))
			return false;
		if (dataDe == null) {
			if (other.dataDe != null)
				return false;
		} else if (!dataDe.equals(other.dataDe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RangeDatas [dataDe=" + dataDe + ", dataAte=" + dataAte + "]";
	}

}
