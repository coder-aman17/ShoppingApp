package com.example.userapp.data.repo

import com.example.userapp.common.ResultState
import com.example.userapp.common.USER_COLLECTION
import com.example.userapp.domain.models.UserData
import com.example.userapp.domain.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
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


}