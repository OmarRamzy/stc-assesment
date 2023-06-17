package com.stc.task.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "folder")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends Item implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    @JsonIgnore
    protected Space space;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<File> files = new ArrayList<>();


}
