package com.stc.task.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "space")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Space extends Item implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    protected List<Folder> folders = new ArrayList<>();


}
