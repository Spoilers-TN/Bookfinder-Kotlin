package it.tn.spoilers.database.services

import it.tn.spoilers.database.models.Reviews
import it.tn.spoilers.extras.GenerateUUID
import org.litote.kmongo.Id
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

class ReviewsService {
    private val client = KMongo.createClient("")
    private val database = client.getDatabase("bookfinder")
    private val reviewsCollection = database.getCollection<Reviews>("Reviews")

    fun create(review: Reviews): Id<Reviews>?  {
        reviewsCollection.insertOne(review)
        return review.id
    }

    fun assistedCreate(title: String, message: String, sender: String, recipient: String): Id<Reviews>?  {
        val review = Reviews(null, GenerateUUID(), title, message, sender, recipient)
        this.create(review)
        return review.id
    }

    fun findByRecipient(userID: String): List<Reviews> {
        val caseSensitiveTypeSafeFilter = Reviews::Review_Recipient eq userID
        return reviewsCollection.find(caseSensitiveTypeSafeFilter)
            .toList()
    }

    fun findBySender(userID: String): List<Reviews> {
        val caseSensitiveTypeSafeFilter = Reviews::Review_Sender eq userID
        return reviewsCollection.find(caseSensitiveTypeSafeFilter)
            .toList()
    }

}