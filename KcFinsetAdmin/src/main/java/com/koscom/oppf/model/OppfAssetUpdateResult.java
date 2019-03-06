package com.koscom.oppf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017-10-20.
 */
public class OppfAssetUpdateResult {
    public List<OppfAssetJsonArray> list;

    public OppfAssetUpdateResult(){
        list = new ArrayList<OppfAssetJsonArray>();
    }

}
