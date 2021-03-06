package cgeo.geocaching.loaders;

import cgeo.geocaching.SearchResult;
import cgeo.geocaching.connector.ConnectorFactory;
import cgeo.geocaching.connector.capability.ISearchByOwner;

import org.eclipse.jdt.annotation.NonNull;

import android.content.Context;

public class OwnerGeocacheListLoader extends AbstractSearchLoader {

    private final @NonNull String username;

    public OwnerGeocacheListLoader(final Context context, final @NonNull String username) {
        super(context);
        this.username = username;
    }

    @Override
    public SearchResult runSearch() {
        SearchResult searchResult = new SearchResult();

        for (ISearchByOwner connector : ConnectorFactory.getSearchByOwnerConnectors()) {
            if (connector.isActive()) {
                searchResult.addSearchResult(connector.searchByOwner(username, this));
            }
        }

        return searchResult;
    }

}
