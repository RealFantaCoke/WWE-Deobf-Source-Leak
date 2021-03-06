package org.spongepowered.tools.obfuscation.mapping.mcp;

import com.google.common.io.*;
import com.google.common.collect.*;
import com.google.common.base.*;
import org.spongepowered.asm.obfuscation.mapping.mcp.*;
import org.spongepowered.asm.mixin.throwables.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import java.io.*;

class MappingProviderSrg$1 implements LineProcessor<String> {
    final BiMap val$packageMap;
    final BiMap val$classMap;
    final BiMap val$fieldMap;
    final BiMap val$methodMap;
    final File val$input;
    final MappingProviderSrg this$0;
    
    MappingProviderSrg$1(final MappingProviderSrg this$0, final BiMap val$packageMap, final BiMap val$classMap, final BiMap val$fieldMap, final BiMap val$methodMap, final File val$input) {
        this.this$0 = this$0;
        this.val$packageMap = val$packageMap;
        this.val$classMap = val$classMap;
        this.val$fieldMap = val$fieldMap;
        this.val$methodMap = val$methodMap;
        this.val$input = val$input;
        super();
    }
    
    public String getResult() {
        return null;
    }
    
    public boolean processLine(final String string) throws IOException {
        if (Strings.isNullOrEmpty(string) || string.startsWith("#")) {
            return true;
        }
        final String substring = string.substring(0, 2);
        final String[] split = string.substring(4).split(" ");
        if (substring.equals("PK")) {
            this.val$packageMap.forcePut((Object)split[0], (Object)split[1]);
        }
        else if (substring.equals("CL")) {
            this.val$classMap.forcePut((Object)split[0], (Object)split[1]);
        }
        else if (substring.equals("FD")) {
            this.val$fieldMap.forcePut((Object)new MappingFieldSrg(split[0]).copy(), (Object)new MappingFieldSrg(split[1]).copy());
        }
        else {
            if (!substring.equals("MD")) {
                throw new MixinException("Invalid SRG file: " + this.val$input);
            }
            this.val$methodMap.forcePut((Object)new MappingMethod(split[0], split[1]), (Object)new MappingMethod(split[2], split[3]));
        }
        return true;
    }
    
    public Object getResult() {
        return this.getResult();
    }
}