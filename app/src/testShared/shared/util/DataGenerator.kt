package shared.util

import com.marko.githubapp.domain.Repo
import com.marko.githubapp.domain.User
import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.domain.commit.CommitData

object DataGenerator {

    val user = User(
        name = "Marko",
        imageUrl = "",
        company = "NLB",
        bio = "Hello",
        followers = 15,
        following = 13
    )

    val repos = arrayListOf<Repo>().apply {
        add(
            Repo(
                id = 0,
                name = "app 1",
                description = "description",
                openIssuesCount = "11",
                language = "Kotlin"
            )
        )
        add(
            Repo(
                id = 1,
                name = "app 2",
                description = "description",
                openIssuesCount = "1",
                language = "Java"
            )
        )
        add(
            Repo(
                id = 2,
                name = "app 3",
                description = "description",
                openIssuesCount = "21",
                language = "Dart"
            )
        )
    }

    val commits = arrayListOf<Commit>().apply {
        add(
            Commit(
                sha = "asd",
                commitUrl = "https://www.google.rs",
                commitData = CommitData(
                    authorName = "Marko",
                    commitDate = "30.01.2520",
                    message = "Message",
                    commentCount = "21",
                    isVerified = true
                )
            )
        )
        add(
            Commit(
                sha = "dda",
                commitUrl = "url",
                commitData = CommitData(
                    authorName = "Steve",
                    commitDate = "24.03.1999",
                    message = "Message",
                    commentCount = "1",
                    isVerified = false
                )
            )
        )
        add(
            Commit(
                sha = "qqq",
                commitUrl = "url",
                commitData = CommitData(
                    authorName = "Vince",
                    commitDate = "12.12.2120",
                    message = "Message",
                    commentCount = "5",
                    isVerified = true
                )
            )
        )
    }
}