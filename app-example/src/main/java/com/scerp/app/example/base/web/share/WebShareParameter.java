package com.scerp.app.example.base.web.share;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ModelAttribute
 */
@Component
public class WebShareParameter implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return "shareParameter".equals(methodParameter.getParameterName());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //nativeWebRequest.setAttribute("shareParameter","shareParameter", RequestAttributes.SCOPE_REQUEST);
        return "shareParameter";
    }

    /**
     * initialize the model prior to any @RequestMapping method invocation.
     *\/
     @ModelAttribute("default") public Model modelHandle(Model model, HttpServletRequest request) {
     return model.addAttribute("shareParameter", "shareParameter");
     }*/

}
