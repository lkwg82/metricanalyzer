package de.lgohlke.io;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

/**
 * <p>
 * JavaSourceFileFinder class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class JavaSourceFileFinder
{
	private final Set<IFileVisitor> visitors = new HashSet<IFileVisitor>();

	/**
	 * <p>
	 * scan.
	 * </p>
	 */
	public JavaSourceFileFinder scanDirectory(final File file)
	{

		if (file.isDirectory())
		{
			IOFileFilter fileFilter = FileFilterUtils.and(//
					HiddenFileFilter.VISIBLE, //
					FileFilterUtils.fileFileFilter(),//
					FileFilterUtils.suffixFileFilter("java", IOCase.INSENSITIVE)//
					);
			IOFileFilter directoryFilter = FileFilterUtils.and(HiddenFileFilter.VISIBLE, FileFilterUtils.directoryFileFilter());
			List<File> files = new ArrayList<File>(FileUtils.listFiles(file, fileFilter, directoryFilter));

			for (File f : files)
			{
				for (IFileVisitor visitor : visitors)
				{
					visitor.visit(f);
				}
			}
		}
		else
		{

		}
		return this;
	}

	public JavaSourceFileFinder addVisitor(final IFileVisitor iFileVisitor)
	{
		if (!visitors.add(iFileVisitor))
		{
			log.warn("visitor already set : " + iFileVisitor);
		}
		return this;
	}
}
