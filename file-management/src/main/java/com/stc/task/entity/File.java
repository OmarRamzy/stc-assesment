package com.stc.task.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "File")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class File extends Item implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Lob
    @Column
    private byte[] binaries;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    protected Folder folder;


	@Override
	public String toString() {
		return "File [folder=" + folder + "]";
	}

    
    
}
