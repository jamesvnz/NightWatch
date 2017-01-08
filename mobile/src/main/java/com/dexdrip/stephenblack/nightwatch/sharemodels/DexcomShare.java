
package com.dexdrip.stephenblack.nightwatch.sharemodels;


import com.dexdrip.stephenblack.nightwatch.model.ShareGlucose;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by stephenblack on 3/16/15.
 */
public interface DexcomShare {

    @POST("General/LoginPublisherAccountByName")
    Call<String> getSessionId(@Body Map<String, String> body);
    //Since this seems to respond with a string we need a delegate that will parse the response body
    //new String(((TypedByteArray) response.getBody()).getBytes());

    @POST("Publisher/IsRemoteMonitoringSessionActive")
    Call<Boolean> checkSessionActive(@Query("sessionId") String sessionId);
    // needs ?sessionId={YourSessionId}
    // returns true or false

    @POST("Publisher/StartRemoteMonitoringSession")
    Call<ResponseBody> StartRemoteMonitoringSession(@Query("sessionId") String sessionId,
                                                    @Query("serialNumber") String serialNumber);
    // needs ?sessionId={YourSessionId}&serialNumber={YourdexcomSerialNumber}
    // returns status code

    @POST("Publisher/PostReceiverEgvRecords")
    Call<ResponseBody> uploadBGRecords(@Query("sessionId") String sessionId, @Body ShareUploadPayload payload);
    // needs ?sessionId={YourSessionId}
    // body ShareUploadPayload
    // returns status code

    @POST("General/AuthenticatePublisherAccount")
    Call<String> authenticatePublisherAccount(@Query("sessionId") String sessionId,
                                                    @Query("serialNumber") String serialNumber,
                                                    @Body Map<String, String> body);
    // maybe needs ?sessionId={YourSessionId}&serialNumber={YourdexcomSerialNumber}
    // body ShareUploadPayload
    // returns status code

    @POST("Publisher/CheckMonitoredReceiverAssignmentStatus")
    Call<String> checkMonitorAssignment(@Query("sessionId") String sessionId,
                                              @Query("serialNumber") String serialNumber);
    // needs ?sessionId={YourSessionId}&serialNumber={YourdexcomSerialNumber}
    // returns `AssignedToYou` or `NotAssigned`

    @POST("Publisher/ReplacePublisherAccountMonitoredReceiver")
    Call<ResponseBody> updateMonitorAssignment(@Query("sessionId") String sessionId,
                                               @Query("serialNumber") String serialNumber);

    @POST("Publisher/ReadPublisherLatestGlucoseValues")
    Call<List<ShareGlucose>> getBgReadings(@Query("sessionId") String sessionId,
                                   @Query("minutes") int minutes,
                                   @Query("maxcount") int maxcount);


    @POST("Publisher/UpdatePublisherAccountRuntimeInfo")
    Call<ResponseBody> updatePublisherAccountInfo(@Body UserAgent body);
    //Since this seems to respond with a string we need a delegate that will parse the response body
    //new String(((TypedByteArray) response.getBody()).getBytes());



    //Follower Related
    @POST("Publisher/ListPublisherAccountSubscriptions")
    Call<List<ExistingFollower>> getContacts(@Query("sessionId") String sessionId);
    // needs ?sessionId={YourSessionId}
    // returns
    // [
    //    {
    //        "ContactId":"FollowersContactId",
    //            "ContactName":"FollowersName",
    //            "DateTimeCreated":{
    //        "DateTime":"\/Date(1437101121008)\/",
    //                "OffsetMinutes":0
    //          },
    //        "DateTimeModified":{
    //        "DateTime":"\/Date(1437101121008)\/",
    //                "OffsetMinutes":0
    //          },
    //        "DisplayName":"YourDisplayName",
    //            "InviteExpires":{
    //        "DateTime":"\/Date(1437705921008)\/",
    //                "OffsetMinutes":0
    //          },
    //        "IsEnabled":false,
    //        "IsMonitoringSessionActive":true,
    //        "Permissions":1,
    //        "State":2,
    //        "SubscriberId":"00000000-0000-0000-0000-000000000000",
    //        "SubscriptionId":"theirSubscriptionIdIsuppose?"
    //    }
    //]

    @POST("Publisher/DoesContactExistByName")
    @Headers({"Content-Length: 0"})
    Call<ResponseBody> doesContactExist(@Query("sessionId") String sessionId,
                                        @Query("contactName") String contactName);
    // needs ?sessionId={YourSessionId}&contactName={newcontactName}
    // returns true or false

    @POST("Publisher/CreateContact")
    @Headers({"Content-Length: 0"})
    Call<String> createContact(@Query("sessionId") String sessionId,
                                     @Query("contactName") String contactName,
                                     @Query("emailAddress") String emailAddress);
    // needs ?sessionId={YourSessionId}&contactName={newcontactName}&emailAddress={FollowerEmail}
    // returns a contact id

    @POST("Publisher/CreateSubscriptionInvitation")
    Call<String> createInvitationForContact(@Query("sessionId") String sessionId,
                                                  @Query("contactId") String contactId,
                                                  @Body InvitationPayload body);
    // needs ?sessionId={YourSessionId}&contactId={ContactId}
    // returns a contact id

    @POST("Publisher/DeleteContact")
    @Headers({"Content-Length: 0"})
    Call<ResponseBody> deleteContact(@Query("sessionId") String sessionId,
                                     @Query("contactId") String contactId);
    // needs ?sessionId={YourSessionId}&contactId={foll`owersContactId}
    // just a status

}
