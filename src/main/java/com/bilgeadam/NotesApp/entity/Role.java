package com.bilgeadam.NotesApp.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority
{
	private static final long serialVersionUID = 3661468982812594323L;

	@Id
	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	public Role()
	{
	}

	public Role(String name)
	{
		this.name = name;
	}

	@Override
	public String getAuthority()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "Role [name=" + name + "]";
	}
}