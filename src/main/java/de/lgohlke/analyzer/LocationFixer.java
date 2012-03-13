package de.lgohlke.analyzer;

import java.util.ArrayList;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.sonar.squid.api.Query;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceCodeSearchEngine;

import de.lgohlke.AST.Location;

@Slf4j
@RequiredArgsConstructor
public class LocationFixer
{
	private final SourceCodeSearchEngine searchEngine;

	public SourceCode fixLocation(final Location location)
	{

		SourceCode code = searchEngine.search(location.getKey());
		if (code == null)
		{
			final String key = location.getClazz().replaceAll("\\.", "/");
			final String method = location.getKey().replaceAll(".*#", "#");

			Collection<SourceCode> codeLists = searchEngine.search(new Query()
			{
				@Override
				public boolean match(final SourceCode unit)
				{
					return unit.getKey().startsWith(key) && unit.getKey().endsWith(method);
				}
			});

			if (codeLists.size() == 1)
			{
				code = new ArrayList<SourceCode>(codeLists).get(0);
				if (log.isDebugEnabled())
				{
					log.debug("fix location key for " + location.getKey() + " -> " + code.getKey());
				}
				location.setKey(code.getKey());
			}
			else
			{
				log.error("nothing found for key: " + location.getKey());
			}
		}
		else
		{
			if (log.isDebugEnabled()) {
				log.debug("did not need to fix  for " + location.getKey());
			}
		}
		return code;

	}
}
