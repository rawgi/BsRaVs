package de.hsrm.cs.wwwvs.filesystem.webservice;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * Einfache Klasse um Inforationen über eine Datei
 * halten zu können. Die Attribute sind public und
 * können direkt zugegriffen werden.
 */
@XmlAccessorType( XmlAccessType.FIELD )
public class FileInfo {
	public int parent;
	public String name;
	public int size;
}
