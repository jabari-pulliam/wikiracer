package wikiracer;


import java.util.*;

public class PathFinder {

    /**
     * Finds the path from a start URL to an end URL
     *
     * @param start The start URL
     * @param end The end URL
     * @return A list containing the path between the start and end
     */
    public List<String> findPath(String start, String end) {
        // Store the URLs we've already visited to avoid redundant work
        Set<String> visitedUrls = new HashSet<>();

        // Queue to store the links to process
        Queue<LinkWrapper> urlQueue = new ArrayDeque<>();

        // Start off with the start URL in the queue
        urlQueue.add(new LinkWrapper(start, null));

        // Perform BFS until we find the target link
        while (!urlQueue.isEmpty()) {
            LinkWrapper currentLink = urlQueue.poll();

            // Check to see if this is a previously undiscovered link and
            //   only proceed if it is
            if (!visitedUrls.contains(currentLink.getUrl())) {
                // Parse the links from the current URL and add
                //   them to the queue
                DocParser parser = new DocParser();
                for (String url : parser.findLinks(currentLink.getUrl())) {
                    LinkWrapper childLink = new LinkWrapper(url, currentLink);

                    // Check if we've found our target
                    if (url.equals(end))
                        return buildPath(childLink);

                    urlQueue.add(childLink);
                }

                // Save this link as already visited
                visitedUrls.add(currentLink.getUrl());
            }
        }

        return null;
    }

    /**
     * Build a list of the path to the given link
     *
     * @param linkWrapper The link
     * @return The path list
     */
    private List<String> buildPath(LinkWrapper linkWrapper) {
        Stack<String> urlStack = new Stack<>();
        LinkWrapper current = linkWrapper;
        while (current != null) {
            urlStack.push(current.getUrl());
            current = current.getParent();
        }

        List<String> path = new ArrayList<>();
        while (!urlStack.isEmpty())
            path.add(urlStack.pop());

        return path;
    }

    /**
     * Wraps a URL link, allowing us to remember its parent
     */
    private static class LinkWrapper {
        private final LinkWrapper parent;
        private final String url;

        LinkWrapper(String url, LinkWrapper parent) {
            this.url = url;
            this.parent = parent;
        }

        String getUrl() {
            return url;
        }

        LinkWrapper getParent() {
            return parent;
        }
    }

}
