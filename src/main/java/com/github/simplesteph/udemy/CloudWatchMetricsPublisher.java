package com.github.simplesteph.udemy;
//
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.util.EC2MetadataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudWatchMetricsPublisher{

    public void publishResponseTime(String apiName, long milliSeconds, AmazonCloudWatch cw){

        Dimension instanceIdDimension = new Dimension()
                .withName("InstanceId")
                .withValue(EC2MetadataUtils.getInstanceId());

        Dimension apiNameDimension = new Dimension()
                .withName("API")
                .withValue(apiName);

        List<Dimension> dimensions = new ArrayList<>();
        dimensions.add(instanceIdDimension);
        dimensions.add(apiNameDimension);

        MetricDatum datum = new MetricDatum()
                .withMetricName("API_Response_Time")
                .withUnit(StandardUnit.Milliseconds)
                .withValue(Double.valueOf(milliSeconds))
                .withDimensions(dimensions);

        PutMetricDataRequest request = new PutMetricDataRequest()
                .withNamespace("EC2")
                .withMetricData(datum);

        PutMetricDataResult response = cw.putMetricData(request);

    }

}