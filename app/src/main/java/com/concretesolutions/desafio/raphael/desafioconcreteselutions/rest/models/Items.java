package com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models;

import org.parceler.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Raphael on 11/10/2016.
 */

public class Items implements Comparable
{
    private String has_issues;

    private String teams_url;

    private String score;

    private String compare_url;

    private String releases_url;

    private String keys_url;

    private String has_pages;

    private String description;

    private String milestones_url;

    private String has_wiki;

    private String events_url;

    private String archive_url;

    private String subscribers_url;

    private String contributors_url;

    private String pushed_at;

    private String fork;

    private String svn_url;

    private String collaborators_url;

    private String subscription_url;

    private String clone_url;

    private String trees_url;

    private String homepage;

    private String url;

    private String size;

    private String notifications_url;

    private String deployments_url;

    private String updated_at;

    private String branches_url;

    private Owner owner;

    private String issue_events_url;

    private String language;

    private String forks_count;

    private String contents_url;

    private String watchers_count;

    private String blobs_url;

    private String commits_url;

    private String has_downloads;

    private String git_commits_url;

    private String default_branch;

    private String open_issues;

    private int id;

    private String downloads_url;

    private String mirror_url;

    private String comments_url;

    private String name;

    private String created_at;

    private String stargazers_count;

    private String assignees_url;

    private String pulls_url;

    private String watchers;

    private String stargazers_url;

    private String hooks_url;

    private String languages_url;

    private String issues_url;

    private String git_tags_url;

    private String merges_url;

    private String git_refs_url;

    private String open_issues_count;

    private String ssh_url;

    private String html_url;

    private String forks;

    private String statuses_url;

    private String forks_url;

    private String issue_comment_url;

    private String labels_url;

    private String git_url;

    private String tags_url;

    private String full_name;

    public String getHas_issues ()
    {
        return has_issues;
    }

    public void setHas_issues (String has_issues)
    {
        this.has_issues = has_issues;
    }

    public String getTeams_url ()
    {
        return teams_url;
    }

    public void setTeams_url (String teams_url)
    {
        this.teams_url = teams_url;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public String getCompare_url ()
    {
        return compare_url;
    }

    public void setCompare_url (String compare_url)
    {
        this.compare_url = compare_url;
    }

    public String getReleases_url ()
    {
        return releases_url;
    }

    public void setReleases_url (String releases_url)
    {
        this.releases_url = releases_url;
    }

    public String getKeys_url ()
    {
        return keys_url;
    }

    public void setKeys_url (String keys_url)
    {
        this.keys_url = keys_url;
    }

    public String getHas_pages ()
    {
        return has_pages;
    }

    public void setHas_pages (String has_pages)
    {
        this.has_pages = has_pages;
    }

    public String getDescription ()
    {
        return description != null ? description : "Descrição não disponível.";
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getMilestones_url ()
    {
        return milestones_url;
    }

    public void setMilestones_url (String milestones_url)
    {
        this.milestones_url = milestones_url;
    }

    public String getHas_wiki ()
    {
        return has_wiki;
    }

    public void setHas_wiki (String has_wiki)
    {
        this.has_wiki = has_wiki;
    }

    public String getEvents_url ()
    {
        return events_url;
    }

    public void setEvents_url (String events_url)
    {
        this.events_url = events_url;
    }

    public String getArchive_url ()
    {
        return archive_url;
    }

    public void setArchive_url (String archive_url)
    {
        this.archive_url = archive_url;
    }

    public String getSubscribers_url ()
    {
        return subscribers_url;
    }

    public void setSubscribers_url (String subscribers_url)
    {
        this.subscribers_url = subscribers_url;
    }

    public String getContributors_url ()
    {
        return contributors_url;
    }

    public void setContributors_url (String contributors_url)
    {
        this.contributors_url = contributors_url;
    }

    public String getPushed_at ()
    {
        return pushed_at;
    }

    public void setPushed_at (String pushed_at)
    {
        this.pushed_at = pushed_at;
    }

    public String getFork ()
    {
        return fork;
    }

    public void setFork (String fork)
    {
        this.fork = fork;
    }

    public String getSvn_url ()
    {
        return svn_url;
    }

    public void setSvn_url (String svn_url)
    {
        this.svn_url = svn_url;
    }

    public String getCollaborators_url ()
    {
        return collaborators_url;
    }

    public void setCollaborators_url (String collaborators_url)
    {
        this.collaborators_url = collaborators_url;
    }

    public String getSubscription_url ()
    {
        return subscription_url;
    }

    public void setSubscription_url (String subscription_url)
    {
        this.subscription_url = subscription_url;
    }

    public String getClone_url ()
    {
        return clone_url;
    }

    public void setClone_url (String clone_url)
    {
        this.clone_url = clone_url;
    }

    public String getTrees_url ()
    {
        return trees_url;
    }

    public void setTrees_url (String trees_url)
    {
        this.trees_url = trees_url;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    public String getNotifications_url ()
    {
        return notifications_url;
    }

    public void setNotifications_url (String notifications_url)
    {
        this.notifications_url = notifications_url;
    }

    public String getDeployments_url ()
    {
        return deployments_url;
    }

    public void setDeployments_url (String deployments_url)
    {
        this.deployments_url = deployments_url;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getBranches_url ()
    {
        return branches_url;
    }

    public void setBranches_url (String branches_url)
    {
        this.branches_url = branches_url;
    }

    public Owner getOwner ()
    {
        return owner  != null ? owner : new Owner();
    }

    public void setOwner (Owner owner)
    {
        this.owner = owner;
    }

    public String getIssue_events_url ()
    {
        return issue_events_url;
    }

    public void setIssue_events_url (String issue_events_url)
    {
        this.issue_events_url = issue_events_url;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getForks_count ()
    {
        return forks_count != null ? forks_count : "N/D";
    }

    public void setForks_count (String forks_count)
    {
        this.forks_count = forks_count;
    }

    public String getContents_url ()
    {
        return contents_url;
    }

    public void setContents_url (String contents_url)
    {
        this.contents_url = contents_url;
    }

    public String getWatchers_count ()
    {
        return watchers_count;
    }

    public void setWatchers_count (String watchers_count)
    {
        this.watchers_count = watchers_count;
    }

    public String getBlobs_url ()
    {
        return blobs_url;
    }

    public void setBlobs_url (String blobs_url)
    {
        this.blobs_url = blobs_url;
    }

    public String getCommits_url ()
    {
        return commits_url;
    }

    public void setCommits_url (String commits_url)
    {
        this.commits_url = commits_url;
    }

    public String getHas_downloads ()
    {
        return has_downloads;
    }

    public void setHas_downloads (String has_downloads)
    {
        this.has_downloads = has_downloads;
    }

    public String getGit_commits_url ()
    {
        return git_commits_url;
    }

    public void setGit_commits_url (String git_commits_url)
    {
        this.git_commits_url = git_commits_url;
    }

//    public String getPrivate ()
//    {
//        return private;
//    }
//
//    public void setPrivate (String private)
//    {
//        this.private = private;
//    }

    public String getDefault_branch ()
    {
        return default_branch;
    }

    public void setDefault_branch (String default_branch)
    {
        this.default_branch = default_branch;
    }

    public String getOpen_issues ()
    {
        return open_issues;
    }

    public void setOpen_issues (String open_issues)
    {
        this.open_issues = open_issues;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getDownloads_url ()
    {
        return downloads_url;
    }

    public void setDownloads_url (String downloads_url)
    {
        this.downloads_url = downloads_url;
    }

    public String getComments_url ()
    {
        return comments_url;
    }

    public void setComments_url (String comments_url)
    {
        this.comments_url = comments_url;
    }

    public String getName ()
    {
        return name != null ? name : "N/D";
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getStargazers_count ()
    {
        return stargazers_count != null ? stargazers_count : "N/D";
    }

    public void setStargazers_count (String stargazers_count)
    {
        this.stargazers_count = stargazers_count;
    }

    public String getAssignees_url ()
    {
        return assignees_url;
    }

    public void setAssignees_url (String assignees_url)
    {
        this.assignees_url = assignees_url;
    }

    public String getPulls_url ()
    {
        return pulls_url;
    }

    public void setPulls_url (String pulls_url)
    {
        this.pulls_url = pulls_url;
    }

    public String getWatchers ()
    {
        return watchers;
    }

    public void setWatchers (String watchers)
    {
        this.watchers = watchers;
    }

    public String getStargazers_url ()
    {
        return stargazers_url;
    }

    public void setStargazers_url (String stargazers_url)
    {
        this.stargazers_url = stargazers_url;
    }

    public String getHooks_url ()
    {
        return hooks_url;
    }

    public void setHooks_url (String hooks_url)
    {
        this.hooks_url = hooks_url;
    }

    public String getLanguages_url ()
    {
        return languages_url;
    }

    public void setLanguages_url (String languages_url)
    {
        this.languages_url = languages_url;
    }

    public String getIssues_url ()
    {
        return issues_url;
    }

    public void setIssues_url (String issues_url)
    {
        this.issues_url = issues_url;
    }

    public String getGit_tags_url ()
    {
        return git_tags_url;
    }

    public void setGit_tags_url (String git_tags_url)
    {
        this.git_tags_url = git_tags_url;
    }

    public String getMerges_url ()
    {
        return merges_url;
    }

    public void setMerges_url (String merges_url)
    {
        this.merges_url = merges_url;
    }

    public String getGit_refs_url ()
    {
        return git_refs_url;
    }

    public void setGit_refs_url (String git_refs_url)
    {
        this.git_refs_url = git_refs_url;
    }

    public String getOpen_issues_count ()
    {
        return open_issues_count;
    }

    public void setOpen_issues_count (String open_issues_count)
    {
        this.open_issues_count = open_issues_count;
    }

    public String getSsh_url ()
    {
        return ssh_url;
    }

    public void setSsh_url (String ssh_url)
    {
        this.ssh_url = ssh_url;
    }

    public String getHtml_url ()
    {
        return html_url;
    }

    public void setHtml_url (String html_url)
    {
        this.html_url = html_url;
    }

    public String getForks ()
    {
        return forks;
    }

    public void setForks (String forks)
    {
        this.forks = forks;
    }

    public String getStatuses_url ()
    {
        return statuses_url;
    }

    public void setStatuses_url (String statuses_url)
    {
        this.statuses_url = statuses_url;
    }

    public String getForks_url ()
    {
        return forks_url;
    }

    public void setForks_url (String forks_url)
    {
        this.forks_url = forks_url;
    }

    public String getIssue_comment_url ()
    {
        return issue_comment_url;
    }

    public void setIssue_comment_url (String issue_comment_url)
    {
        this.issue_comment_url = issue_comment_url;
    }

    public String getLabels_url ()
    {
        return labels_url;
    }

    public void setLabels_url (String labels_url)
    {
        this.labels_url = labels_url;
    }

    public String getGit_url ()
    {
        return git_url;
    }

    public void setGit_url (String git_url)
    {
        this.git_url = git_url;
    }

    public String getTags_url ()
    {
        return tags_url;
    }

    public void setTags_url (String tags_url)
    {
        this.tags_url = tags_url;
    }

    public String getFull_name ()
    {
        return full_name != null ? full_name : "N/D";
    }

    public void setFull_name (String full_name)
    {
        this.full_name = full_name;
    }

    @Override
    public String toString()
    {
        return "Items [has_issues = "+has_issues+", teams_url = "+teams_url+", score = "+score+
                ", compare_url = "+compare_url+", releases_url = "+releases_url+", keys_url = "
                +keys_url+", has_pages = "+has_pages+", description = "+description+
                ", milestones_url = "+milestones_url+", has_wiki = "+has_wiki+", events_url = "
                +events_url+", archive_url = "+archive_url+", subscribers_url = "+subscribers_url
                +", contributors_url = "+contributors_url+", pushed_at = "+pushed_at+", fork = "
                +fork+", svn_url = "+svn_url+", collaborators_url = "+collaborators_url+
                ", subscription_url = "+subscription_url+", clone_url = "+clone_url+
                ", trees_url = "+trees_url+", homepage = "+homepage+", url = "+url+
                ", size = "+size+", notifications_url = "+notifications_url+
                ", deployments_url = "+deployments_url+", updated_at = "+updated_at+
                ", branches_url = "+branches_url+", owner = "+owner+", issue_events_url = "
                +issue_events_url+", language = "+language+", forks_count = "+forks_count
                +", contents_url = "+contents_url+", watchers_count = "+watchers_count+
                ", blobs_url = "+blobs_url+", commits_url = "+commits_url+", has_downloads = "
                +has_downloads+", git_commits_url = "+git_commits_url+", default_branch = "
                +default_branch+", open_issues = "+open_issues+", id = "+id+", downloads_url = "
                +downloads_url+", mirror_url = "+mirror_url+", comments_url = "+comments_url+
                ", name = "+name+", created_at = "+created_at+", stargazers_count = "
                +stargazers_count+", assignees_url = "+assignees_url+", pulls_url = "+pulls_url+
                ", watchers = "+watchers+", stargazers_url = "+stargazers_url+", hooks_url = "
                +hooks_url+", languages_url = "+languages_url+", issues_url = "+issues_url+
                ", git_tags_url = "+git_tags_url+", merges_url = "+merges_url+", git_refs_url = "
                +git_refs_url+", open_issues_count = "+open_issues_count+", ssh_url = "+ssh_url+
                ", html_url = "+html_url+", forks = "+forks+", statuses_url = "+statuses_url+
                ", forks_url = "+forks_url+", issue_comment_url = "+issue_comment_url+
                ", labels_url = "+labels_url+", git_url = "+git_url+", tags_url = "+tags_url+
                ", full_name = "+full_name+"]";
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof FakeItem)
            return -1;
        else {
            Items t = (Items) o;
            if (isIdGreaterThan(t))
                return -1;
            else
                return 1;
        }
    }

    private boolean isIdGreaterThan(Items t) {
        if (getId() > t.getId()) {
            return true;
        } else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items)) return false;

        Items items = (Items) o;

        return getId() == items.getId();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(id).
                        toHashCode();
    }
}
