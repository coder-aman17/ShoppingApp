package com.example.userapp.data.repo

import android.net.Uri
import com.example.userapp.common.ResultState
import com.example.userapp.common.USER_COLLECTION
import com.example.userapp.domain.models.CategoryDataModels
import com.example.userapp.domain.models.ProductDataModels
import com.example.userapp.domain.models.UserData
import com.example.userapp.domain.models.UserDataParents
import com.example.userapp.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class RepoImpl @Inject constructor(
    var firebaseAuth: FirebaseAuth,
    var firebaseFirestore: FirebaseFirestore
): Repo{

    override fun registerUserWithEmailAndPassword(UserData: UserData): Flow<ResultState<String>>  =
        callbackFlow{
            trySend(ResultState.Loading)

            firebaseAuth.createUserWithEmailAndPassword(UserData.email, UserData.password).addOnCompleteListener {
                if(it.isSuccessful){
                    firebaseFirestore.collection(USER_COLLECTION)
                        .document(it.result.user?.uid.toString()).set(UserData)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                trySend(ResultState.Success("User Registered Successfully and added to firestore" ))
                            }
                            else{
                                if(it.exception!=null){
                                    trySend(ResultState.Error(it.exception?.localizedMessage.toString()))

                                }
                            }
                        }
                    trySend(ResultState.Success("User Registered Successfully"))
                }else{
                    if(it.exception!=null){
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                    }
                }
            }
            awaitClose{
                close()
            }
        }

    override fun loginUserWithEmailAndPassword(UserData: UserData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            firebaseAuth.signInWithEmailAndPassword(UserData.email, UserData.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResultState.Success("User Logged In Successfully"))
                    } else {
                        if (it.exception != null) {
                            trySend(ResultState.Error(it.exception?.localizedMessage.toString()))

                        }
                    }


                }
            awaitClose {
                close()
            }
        }

    override fun getUserById(uid: String): Flow<ResultState<UserDataParents>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseFirestore.collection(USER_COLLECTION).document(uid).get().addOnCompleteListener {
            if(it.isSuccessful){
                val data = it.result.toObject(UserData::class.java)
                val userDataParent  = UserDataParents(it.result.id, data )
                trySend(ResultState.Success(userDataParent))
            }
            else{
                if(it.exception!=null){
                    trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
           }

         }

       }
        awaitClose(){
            close()
        }
    }

    override fun updateUserData(userDataParents: UserDataParents): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        firebaseFirestore.collection(USER_COLLECTION).document(userDataParents.nodeId).update(
            userDataParents.userData!!.toMap()).addOnCompleteListener {
                if(it.isSuccessful){
                    trySend(ResultState.Success("User Data Updated Successfully"))
                }
                else{
                    if(it.exception!=null){
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                }
        }

            }
        awaitClose{
            close()
    }
    

}

    override fun userProfileImage(uri: Uri): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        FirebaseStorage.getInstance().reference.child("userProfileImage/${System.currentTimeMillis()} + ${firebaseAuth.currentUser?.uid}").putFile(uri?: Uri.EMPTY).addOnCompleteListener {
            it.result.storage.downloadUrl.addOnSuccessListener {imageUri->
                trySend(ResultState.Success(imageUri.toString()))
            }
            if(it.exception!=null){
                trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
            }

        }
        awaitClose{
            close()
        }
    }

    override fun getCategoriesInLimited(): Flow<ResultState<List<CategoryDataModels>>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseFirestore.collection("categories").limit(7).get().addOnSuccessListener {querySnapShot->
            val categories  = querySnapShot.documents.mapNotNull { document ->
                document.toObject(CategoryDataModels::class.java)
            }

            trySend(ResultState.Success(categories))

        }.addOnFailureListener {exception->
            trySend(ResultState.Error(exception.toString()))

        }
        awaitClose{
            close()
        }

    }

    override fun getProductsInLimited(): Flow<ResultState<List<ProductDataModels>>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseFirestore.collection("products").limit(7).get().addOnSuccessListener {
            val products = it.documents.mapNotNull { document->
                document.toObject(ProductDataModels::class.java)?.apply {
                    productId = document.id
                }

                }

            trySend(ResultState.Success(products))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose{
            close()

        }
    }

    override fun getAllProducts(): Flow<ResultState<List<ProductDataModels>>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseFirestore.collection("products").get().addOnSuccessListener {
            val products = it.documents.mapNotNull { document->
                document.toObject(ProductDataModels::class.java)?.apply {
                    productId = document.id
                }

            }

            trySend(ResultState.Success(products))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose{
            close()

        }

    }

    override fun getProductsById(productId: String): Flow<ResultState<ProductDataModels>> = callbackFlow {
        trySend(ResultState.Loading)
    }





}