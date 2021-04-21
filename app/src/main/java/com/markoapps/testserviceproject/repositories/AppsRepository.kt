package com.markoapps.testserviceproject.repositories

//import com.markoapps.testserviceproject.db.AppsDao
//import com.markoapps.testserviceproject.db.AppEntity
//import com.markoapps.testserviceproject.network.NetworkApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.withContext

//class AppsRepository(private val appsDataSource: AppsDao, private val networkApi: NetworkApi) {
//
//    val apps : Flow<List<AppModel>> = appsDataSource.getApps().transform {
//        emit(
//            it.map { app ->
//                appEntityToAppModel(app)
//            })
//    }
//
//    suspend fun addApp(app: AppModel) {
//        appsDataSource.addApp(appModelToAppEntity(app))
//    }
//
//    suspend fun updateApps(){
//        withContext(Dispatchers.IO) {
//            val apps = networkApi.getApps().popularApps.map { appModelToAppEntity(it) }
//            appsDataSource.addMultipleDocuments(apps)
//        }
//   }
//
//}
//
//fun appEntityToAppModel(appEntity: AppEntity) = AppModel(
//        appEntity.packageName,
//        appEntity.title,
//        appEntity.rating,
//        appEntity.developer,
//        appEntity.whatIsNew,
//        appEntity.icon)
//
//fun appModelToAppEntity(appModel: AppModel) = AppEntity(
//        appModel.packageName,
//        appModel.title,
//        appModel.rating,
//        appModel.developer,
//        appModel.whatIsNew,
//        appModel.icon)

