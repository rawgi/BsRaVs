package de.hsrm.cs.wwwvs.filesystem.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Einfache Klasse um Inforationen über einen Ordner
 * halten zu können. Die Attribute sind public und
 * können direkt zugegriffen werden.
 */
@XmlAccessorType( XmlAccessType.FIELD )
public class FolderInfo {
	public int parent;
	public String name;
	public int folderCount;
	public int fileCount;
	public int[] files;
	public int[] folders;
}
