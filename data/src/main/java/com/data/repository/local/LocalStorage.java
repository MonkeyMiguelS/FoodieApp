package com.data.repository.local;

import com.domain.commons.JsonUtil;
import com.domain.exceptions.LocalGatewayException;
import com.domain.mappers.Mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

public class LocalStorage {

    private JsonUtil jsonUtil;

    @Inject
    public LocalStorage(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    public <T extends RealmObject> void persist(T model) throws LocalGatewayException {
        Realm realm = null;
        try{
            realm = getInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(model);
            realm.commitTransaction();
        }catch (Exception ex){
            if (realm != null && realm.isInTransaction()){
                realm.cancelTransaction();
            }
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    /**
     * Crea o actualiza e ignora los campos con Null.
     *
     * @param model
     * @param <T>
     * @throws LocalGatewayException
     */
    public <T extends RealmObject> T createOrUpdate(T model) throws LocalGatewayException {
        Realm realm = null;
        T newModel = null;
        try{
            realm = getInstance();
            realm.beginTransaction();
            newModel = (T) realm.createOrUpdateObjectFromJson(model.getClass(), jsonUtil.toJson(model));
            realm.commitTransaction();
        }catch (Exception ex){
            if (realm != null && realm.isInTransaction()){
                realm.cancelTransaction();
            }
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
            return newModel;
        }
    }

    /**
     * Crea o actualiza un listado de objectos e ignora los campos con Null.
     *
     * @param models
     * @param <T>
     * @throws LocalGatewayException
     */
    public <T extends RealmObject> void createOrUpdateAll(List<T> models) throws LocalGatewayException {
        Realm realm = null;
        try{
            if (models != null && models.size() > 0) {
                realm = getInstance();
                realm.beginTransaction();
                realm.createOrUpdateAllFromJson(models.get(0).getClass(), jsonUtil.toJson(models));
                realm.commitTransaction();
            }
        }catch (Exception ex){
            if (realm != null && realm.isInTransaction()){
                realm.cancelTransaction();
            }
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    public <T extends RealmObject> void deleteById(Class<T> classRealm, String id) throws LocalGatewayException{
        Realm realm = null;
        try{
            realm = getInstance();
            T object = realm
                    .where(classRealm)
                    .equalTo("id", id)
                    .findFirst();
            if (object != null){
                realm.beginTransaction();
                object.deleteFromRealm();
                realm.commitTransaction();
            }
        }catch (Exception ex){
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    public <M, T extends RealmObject> M findById(Class<T> classRealm, String id, Mapper<T, M> mapper) throws LocalGatewayException {
        Realm realm = null;
        try{
            realm = getInstance();
            T object = realm
                .where(classRealm)
                .equalTo("id", id)
                .findFirst();

            if (object != null)
                return mapper.map(object);
            else
                return null;

        }catch (Exception ex){
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    public <M, T extends RealmObject> List<M> findAll(Class<T> classRealm, Mapper<T, M> mapper) throws LocalGatewayException {
        Realm realm = null;

        List<M> listM = new ArrayList<>();

        try{
            realm = getInstance();
            RealmResults<T> objects = realm
                    .where(classRealm)
                    .findAll();

            if (objects != null) {
                for (T objectT : objects) {
                    listM.add(mapper.map(objectT));
                }
                return listM;
            }else {
                return null;
            }
        }catch (Exception ex){
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    public <M, T extends RealmObject> List<M> findAllAndOrder(Class<T> classRealm, String orderBy, Mapper<T, M> mapper) throws LocalGatewayException {
        Realm realm = null;

        List<M> listM = new ArrayList<>();

        try{
            realm = getInstance();
            RealmResults<T> objects = realm
                    .where(classRealm)
                    .findAllSorted(orderBy);

            if (objects != null) {
                for (T objectT : objects) {
                    listM.add(mapper.map(objectT));
                }
                return listM;
            }else {
                return null;
            }
        }catch (Exception ex){
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }


    public <M, T extends RealmObject> List<M> findByAttr(Class<T> classRealm, String field, String value, Mapper<T, M> mapper) throws LocalGatewayException {
        Realm realm = null;

        List<M> listM = new ArrayList<>();

        try{
            realm = getInstance();
            RealmResults<T> objects = realm
                    .where(classRealm)
                    .equalTo(field, value)
                    .findAll();

            if (objects != null) {
                for (T objectT : objects) {
                    listM.add(mapper.map(objectT));
                }
                return listM;
            }else {
                return null;
            }
        }catch (Exception ex){
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    public <M, T extends RealmObject> M findMaxValue(Class<T> classRealm, String field, Mapper<T, M> mapper) throws LocalGatewayException {
        Realm realm = null;
        try{
            realm = getInstance();
            T object = realm
                    .where(classRealm)
                    .findAll()
                    .sort(field, Sort.DESCENDING)
                    .first();

            if (object != null)
                return mapper.map(object);
            else
                return null;

        }catch (Exception ex){
            throw new LocalGatewayException();
        }finally {
            if (realm != null)
                realm.close();
        }
    }

    private Realm getInstance(){
        return Realm.getDefaultInstance();
    }
}
