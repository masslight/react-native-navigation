package com.reactnativenavigation.parse;

import com.reactnativenavigation.parse.params.Bool;
import com.reactnativenavigation.parse.params.Button;
import com.reactnativenavigation.parse.params.NullBool;
import com.reactnativenavigation.parse.parsers.BoolParser;
import com.reactnativenavigation.parse.parsers.ColorParser;
import com.reactnativenavigation.parse.parsers.TextParser;
import com.reactnativenavigation.react.Constants;

import org.json.JSONObject;

public class BackButton extends Button {
    public static BackButton parse(JSONObject json) {
        BackButton result = new BackButton();
        if (json == null || json.toString().equals("{}")) return result;

        result.hasValue = true;
        result.visible = BoolParser.parse(json, "visible");
        if (json.has("icon")) result.icon = TextParser.parse(json.optJSONObject("icon"), "uri");
        result.id = json.optString("id", Constants.BACK_BUTTON_ID);
        result.enabled = BoolParser.parse(json, "enabled");
        result.disableIconTint = BoolParser.parse(json, "disableIconTint");
        result.color = ColorParser.parse(json, "color");
        result.disabledColor = ColorParser.parse(json, "disabledColor");
        result.testId = TextParser.parse(json, "testID");
        result.accessibilityLabel = TextParser.parse(json, "accessibilityLabel");

        return result;
    }

    BackButton() {
        id = Constants.BACK_BUTTON_ID;
    }

    public Bool visible = new NullBool();
    private boolean hasValue;

    public boolean hasValue() {
        return hasValue;
    }

    public void mergeWith(BackButton other) {
        if (other.icon.hasValue()) icon = other.icon;
        if (other.visible.hasValue()) visible = other.visible;
        if (other.color.hasValue()) color = other.color;
        if (other.disabledColor.hasValue()) disabledColor = other.disabledColor;
        if (other.disableIconTint.hasValue()) disableIconTint = other.disableIconTint;
        if (other.enabled.hasValue()) enabled = other.enabled;
        if (other.testId.hasValue()) testId = other.testId;
        if (other.accessibilityLabel.hasValue()) accessibilityLabel = other.accessibilityLabel;
    }

    void mergeWithDefault(final BackButton defaultOptions) {
        if (!icon.hasValue()) icon = defaultOptions.icon;
        if (!visible.hasValue()) visible = defaultOptions.visible;
        if (!color.hasValue()) color = defaultOptions.color;
        if (!disabledColor.hasValue()) disabledColor = defaultOptions.disabledColor;
        if (!disableIconTint.hasValue()) disableIconTint = defaultOptions.disableIconTint;
        if (!enabled.hasValue()) enabled = defaultOptions.enabled;
        if (!testId.hasValue()) testId = defaultOptions.testId;
        if (!accessibilityLabel.hasValue()) accessibilityLabel = defaultOptions.accessibilityLabel;
    }

    public void setVisible() {
        visible = new Bool(true);
        hasValue = true;
    }
}
